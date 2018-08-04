package org.xiyoulinux.recruitment.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.model.Join;
import org.xiyoulinux.recruitment.untils.GetInfoFromJW;
import org.xiyoulinux.recruitment.untils.ResponseResult;
import org.xiyoulinux.recruitment.untils.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
@Controller
public class SignUpController {
    @ResponseBody
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseResult getCheckCode(HttpServletRequest request){
        GetInfoFromJW jw = (GetInfoFromJW) request.getSession().getAttribute("JW");
        if(jw == null) {
            return new ResponseResult(new ResponseStatus("请先得到验证码"));
        }
        String sno = request.getParameter("xh");
        String passwd = request.getParameter("mm");
        String checkCode = request.getParameter("yzm");
        String phone = request.getParameter("phone");
        String reason = jw.login(sno,passwd,checkCode);
        if(reason != null){
            return new ResponseResult(new ResponseStatus(reason));
        }
        Join join = jw.getJoin(sno);
        return new ResponseResult(join);
    }

}
