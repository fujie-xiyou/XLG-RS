package org.xiyoulinux.recruitment.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.untils.GetInfoFromJW;
import org.xiyoulinux.recruitment.untils.Md5Until;
import org.xiyoulinux.recruitment.untils.ResponseResult;
import org.xiyoulinux.recruitment.untils.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
@Controller
@ResponseBody
public class SignUpController {
    @Autowired
    private JoinDAO joinDAO;
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseResult signUp(HttpServletRequest request){
        GetInfoFromJW jw = (GetInfoFromJW) request.getSession().getAttribute("JW");
        if(jw == null) {
            return new ResponseResult(new ResponseStatus("请先得到验证码"));
        }
        String sno = request.getParameter("student_no");
        String passwd = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        String mobile = request.getParameter("mobile");
        String reason = jw.login(sno,passwd,checkCode);
        System.out.printf(checkCode);
        if(reason != null){
            return new ResponseResult(new ResponseStatus(reason));
        }
        Join join = jw.getJoin(sno);
        join.setMobile(mobile);
        join.setPasswd(Md5Until.getMd5(passwd));
        join.setProcess_id(0);
        join.setIs_vaild(true);
        if(joinDAO.insert(join) > 0) {
            return new ResponseResult(new ResponseStatus());
        }
        return new ResponseResult(new ResponseStatus("服务器异常"));
    }
    @RequestMapping(value = "/signIn" , method = RequestMethod.POST)
    public ResponseResult signIn(){
        return null;
    }

}
