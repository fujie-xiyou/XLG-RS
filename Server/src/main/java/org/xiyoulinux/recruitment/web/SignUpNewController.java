package org.xiyoulinux.recruitment.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.service.SignService;
import org.xiyoulinux.recruitment.untils.*;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;


@Controller
@ResponseBody
public class SignUpNewController {
    @Autowired
    private SignService signService;

    @RequestMapping(value = "/signUpNew", method = RequestMethod.POST)
    public ResponseResult signUp(HttpServletRequest request ,@RequestParam(value = "mobile") String mobile) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return signService.signUp(request,mobile);

    }
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public ResponseResult checkUser(HttpServletRequest request ,@RequestBody Join join ){
        return signService.checkUser(request,join);
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResponseResult getInfo(HttpServletRequest request){
        return signService.getInfo(request);
    }
    @RequestMapping(value = "isCanSign" ,method = RequestMethod.GET)
    public ResponseResult isCanSign(HttpServletRequest request){
        return signService.isCanSign(request);
    }
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ResponseResult logout(HttpServletRequest request){
        request.getSession().invalidate();
        return new ResponseResult();
    }

}
