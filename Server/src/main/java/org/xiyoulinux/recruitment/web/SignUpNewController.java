package org.xiyoulinux.recruitment.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.untils.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@ResponseBody
public class SignUpNewController {
    @Autowired
    private JoinDAO joinDAO;

    @RequestMapping(value = "/signUpNew", method = RequestMethod.POST)
    public ResponseResult signUp(HttpServletRequest request ,@RequestBody Join join) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String sno = join.getStudent_no();
        String passwd = join.getPasswd();

        ConnectJWGL jw = (ConnectJWGL) request.getSession().getAttribute("JWGL");


        if (jw == null) {
            jw = new ConnectJWGL(sno, passwd);
            request.getSession().setAttribute("JWGL", jw);
        }
        org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult liMengResult = null;
        try {
            liMengResult = jw.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (liMengResult.getFlag()) {
            //学号校验成功


            //当学号密码校验通过后才能得知是否已经报过名
            if (joinDAO.selectByNo(sno) != null) {
                return new ResponseResult(new ResponseStatus("你已经报过名啦!"));
            }
            System.out.println(liMengResult.getData());
            JSONObject jsonObject = JSON.parseObject(liMengResult.getData().toString());
            join.setStudent_no(jsonObject.getString("xh_id"));
            join.setSex(jsonObject.getString("xbm").equals("男") ? "boy" : "girl");
            join.setCollege(jsonObject.getString("jg_id"));
            join.setAdmin_class(jsonObject.getString("bh_id"));
            join.setDept(jsonObject.getString("zszyh_id"));
            join.setCn_name(jsonObject.getString("xm"));
            join.setBirthday(LocalDate.parse(jsonObject.getString("csrq"),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            join.setPasswd(Md5Until.getMd5(passwd));
            join.setProcess_id(0);
            join.setIs_vaild(true);
            join.setStudent_no(sno);
            if (joinDAO.insertSelective(join) > 0) {
                return new ResponseResult(new ResponseStatus());
            } else {
                return new ResponseResult(new ResponseStatus("服务器异常.."));

            }
        } else {
            return new ResponseResult(new ResponseStatus((String) liMengResult.getData()));
        }
    }
}
