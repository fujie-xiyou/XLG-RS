package org.xiyoulinux.recruitment.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.service.SignService;
import org.xiyoulinux.recruitment.untils.ConnectJWGL;
import org.xiyoulinux.recruitment.untils.ResponseResult;
import org.xiyoulinux.recruitment.untils.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("SignSevice")
public class SignServiceImpl implements SignService {
    @Autowired
    private JoinDAO joinDAO;

    @Override
    public ResponseResult isCanSign(HttpServletRequest request) {
        Join join = (Join) request.getSession().getAttribute("join");
        if (join == null) {
            return new ResponseResult(3);
        }
        if (request.getSession().getAttribute("notSign") == null) {
            return new ResponseResult(2);
        }
        return new ResponseResult();
    }

    @Override
    public ResponseResult signUp(HttpServletRequest request, String mobile) {
        if (!checkMobile(mobile)) {
            return new ResponseResult<>("输入不合法");
        }
        Join join = (Join) request.getSession().getAttribute("join");
        if (join == null) {
            return new ResponseResult<>(3, "请先检验学号密码");
        }
        if (request.getSession().getAttribute("notSign") == null) {
            return new ResponseResult<>(2, "我猜你已经报过名了");
        }
        join.setMobile(mobile);
        if (joinDAO.insertSelective(join) > 0) {
            request.getSession().removeAttribute("notSign");
            return new ResponseResult();
        } else {
            return new ResponseResult<>(0, "服务器异常..");
        }
    }

    @Override
    public ResponseResult checkUser(HttpServletRequest request, Join join) {
        String sno = join.getStudent_no();
        String passwd = join.getPasswd();
        if (!checkSnoAndPass(join)) {
            return new ResponseResult<>(0, "输入不合法");
        }
        request.getSession().invalidate();
        ConnectJWGL jw = new ConnectJWGL(sno, passwd);
        org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult liMengResult = null;
        try {
            liMengResult = jw.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (liMengResult.getFlag()) {//学号校验成功
            Join joinFromDB = joinDAO.selectByNo(sno);
            //当学号密码校验通过后才能得知是否已经报过名
            if (joinFromDB != null) {
                request.getSession().setAttribute("join", joinFromDB);
                return new ResponseResult<>(2, "你已经报过名啦！");
                //返回2唯一代表已经报过名！！！
            }
            JSONObject jsonObject = JSON.parseObject(liMengResult.getData().toString());
            join.setAdmin_class(jsonObject.getString("bh_id"));
            join.setCn_name(jsonObject.getString("xm"));
            join.setPasswd(null);
            join.setProcess_id(1);
            join.setIs_vaild(true);
            request.getSession().setAttribute("join", join);
            request.getSession().setAttribute("notSign", "zqn");
            return new ResponseResult();
        } else {
            return new ResponseResult<>(0, (String) liMengResult.getData());
        }
    }

    @Override
    public ResponseResult getInfo(HttpServletRequest request) {
        Join join = (Join) request.getSession().getAttribute("join");

        if (join == null) {
            return new ResponseResult<>(0, "尚未登录");
        } else if (request.getSession().getAttribute("notSign") != null) {
            return new ResponseResult<>(0, "尚未报名");
        } else {
            Join join1 = joinDAO.selectByNo(join.getStudent_no());
            return new ResponseResult<>(join1);
        }
    }
    @Override
    public ResponseResult modifyMobile(HttpSession session, String mobile) {
        if (!checkMobile(mobile)) {
            return new ResponseResult<>("输入不合法");
        }
        Join join = (Join) session.getAttribute("join");
        if (join == null) {
            return new ResponseResult<>(0, "尚未登录");
        } else if (session.getAttribute("notSign") != null) {
            return new ResponseResult<>(0, "尚未报名");
        } else {
            if(joinDAO.updateMobileByNo(mobile,join.getStudent_no()) > 0){
                return new ResponseResult<>();
            }else {
                return new ResponseResult<>("服务器异常");
            }
        }
    }


    private boolean checkSnoAndPass(Join join) {
        Matcher matcher = Pattern.compile("^[012]\\d(\\d{2})\\d{4}$").matcher(join.getStudent_no());
        if (matcher.matches()) {
            int grade = Integer.parseInt(matcher.group(1));
            //TODO 超高耦合度  迟早GG
            if (grade > 19 || grade < 18) {
                return false;
            }
        }
        return join.getPasswd().matches(".{6,26}");
    }

    private boolean checkMobile(String mobile) {
        return mobile.matches("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
    }

}
