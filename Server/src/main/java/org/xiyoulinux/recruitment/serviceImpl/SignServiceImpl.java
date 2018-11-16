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
import java.sql.SQLException;

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
        Join join = (Join) request.getSession().getAttribute("join");
        if (join == null) {
            return new ResponseResult<String>(3, "请先检验学号密码");
        }
        if (request.getSession().getAttribute("notSign") == null) {
            return new ResponseResult<String>(2, "我猜你已经报过名了");
        }
        join.setMobile(mobile);
        if (joinDAO.insertSelective(join) > 0) {
            request.getSession().removeAttribute("notSign");
            return new ResponseResult();
        } else {
            return new ResponseResult<String>(0, "服务器异常..");
        }
    }

    @Override
    public ResponseResult checkUser(HttpServletRequest request, Join join) {
        String sno = join.getStudent_no();
        String passwd = join.getPasswd();
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
                return new ResponseResult<String>(2, "你已经报过名啦！");
                //返回2唯一代表已经报过名！！！
            }
            JSONObject jsonObject = JSON.parseObject(liMengResult.getData().toString());
            join.setAdmin_class(jsonObject.getString("bh_id"));
            join.setCn_name(jsonObject.getString("xm"));
            join.setPasswd(null);
            join.setProcess_id(0);
            join.setIs_vaild(true);
            request.getSession().setAttribute("join", join);
            request.getSession().setAttribute("notSign", "zqn");
            return new ResponseResult();
        } else {
            return new ResponseResult<String>(0, (String) liMengResult.getData());
        }
    }

    @Override
    public ResponseResult getInfo(HttpServletRequest request) {
        Join join = (Join) request.getSession().getAttribute("join");

        if (join == null) {
            return new ResponseResult<String>(0, "尚未登录");
        } else if (request.getSession().getAttribute("notSign") != null) {
            return new ResponseResult<String>(0, "尚未报名");
        } else {
            Join join1 = joinDAO.selectByNo(join.getStudent_no());
            System.out.println(join1);
            return new ResponseResult<Join>(join1);
        }
    }
}
