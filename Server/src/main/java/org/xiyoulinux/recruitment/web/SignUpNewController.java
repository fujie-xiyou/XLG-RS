package org.xiyoulinux.recruitment.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xiyoulinux.recruitment.dao.JoinDAO;
import org.xiyoulinux.recruitment.model.po.Join;
import org.xiyoulinux.recruitment.untils.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class SignUpNewController {
    @Autowired
    private JoinDAO joinDAO;

    @RequestMapping(value = "/signUpNew", method = RequestMethod.POST)
    public ResponseResult signUp(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Join join = new Join();
        String sno = request.getParameter("student_no");
        String real_name = request.getParameter("real_name");
        String admin_class = request.getParameter("admin_class");
        String passwd = request.getParameter("password");
        String url = "https://api.xiyoumobile.com/xiyoulibv2/user/login/?username=" + sno + "&password=" + passwd;
        String data = null;
        try {
            data = doGET(url);
        } catch (Exception e) {
            return new ResponseResult(new ResponseStatus(e.getMessage()));
        }
        Map map = JSON.parseObject(data);
        if (!(boolean) map.get("Result")) {
            return new ResponseResult(new ResponseStatus("学号或密码不正确"));
        }
        String sessionId = null;
        try {
            sessionId = URLEncoder.encode((String) map.get("Detail"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseResult(new ResponseStatus(e.getMessage()));
        }
        url = "https://api.xiyoumobile.com/xiyoulibv2/user/info?session=" + sessionId;
        try {
            data = doGET(url);
        } catch (Exception e) {
            return new ResponseResult(new ResponseStatus(e.getMessage()));
        }
        map = JSON.parseObject(data);
        if (!(boolean) map.get("Result")) {
            return new ResponseResult(new ResponseStatus("获取信息失败，请稍后再试"));
        }
        if (joinDAO.selectByNo(sno) != null) {
            return new ResponseResult(new ResponseStatus("你已经报过名啦!"));
        }
        Map info = (Map) map.get("Detail");
        real_name = (String) info.get("Name");
        admin_class = (String) info.get("Department");
        join.setProcess_id(0);
        join.setIs_vaild(true);
        join.setStudent_no(sno);
        join.setCn_name(real_name);
        join.setAdmin_class(admin_class);
        if (joinDAO.insertSelective(join) > 0) {
            return new ResponseResult(new ResponseStatus());
        } else {
            return new ResponseResult(new ResponseStatus("服务器异常"));
        }
    }

    @RequestMapping(value = "/signUpNew", method = RequestMethod.GET)
    public String test() {
        return "ok!";
    }

    private String doGET(String url) throws Exception {
        InputStream is = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            System.out.println(url);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                is = conn.getInputStream();
                StringBuilder buffer = new StringBuilder();
                byte[] buff = new byte[100];
                int len = 0;
                while ((len = is.read(buff)) > 0) {
                    buffer.append(new String(buff, 0, len, StandardCharsets.UTF_8));
                }
                String data = buffer.toString();
                System.out.println(data);
                return data;
            } else {
                throw new Exception("HTTP Code : " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("连接超时，请稍后再试");
        } finally {
            if (is != null)
                is.close();
        }
    }
}
