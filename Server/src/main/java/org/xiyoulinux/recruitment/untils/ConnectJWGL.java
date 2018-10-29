package org.xiyoulinux.recruitment.untils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xiyoulinux.recruitment.untils.getStuInfo.B64;
import org.xiyoulinux.recruitment.untils.getStuInfo.RSAEncoder;
import org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConnectJWGL {

    private final String url = "http://www.zfjw.xupt.edu.cn";
    private Map<String,String> cookies = new HashMap<>();
    private String modulus;
    private String exponent;
    private String csrftoken;
    private Connection connection;
    private Connection.Response response;
    private Document document;
    private String stuNum;
    private String originalPassword; // 原始密码
    private String password; // 加密后的密码

    public ConnectJWGL(String stuNum,String password){
        this.stuNum = stuNum;
        this.originalPassword = password;
    }

    public org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult init() throws Exception{
        getCsrftoken();
        getRSApublickey();
        return login();
    }

    // 获取csrftoken和Cookies
    private void getCsrftoken(){
        try{
            connection = Jsoup.connect(url+ "/jwglxt/xtgl/login_slogin.html?language=zh_CN&_t="+new Date().getTime());
            connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
            response = connection.execute();
            cookies = response.cookies();
            //保存csrftoken
            document = Jsoup.parse(response.body());
            csrftoken = document.getElementById("csrftoken").val();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    // 获取公钥并加密密码
    private void getRSApublickey() throws Exception{
        connection = Jsoup.connect(url+ "/jwglxt/xtgl/login_getPublicKey.html?" +
                "time="+ new Date().getTime());
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        response = connection.cookies(cookies).ignoreContentType(true).execute();
        JSONObject jsonObject = JSON.parseObject(response.body());
        modulus = jsonObject.getString("modulus");
        exponent = jsonObject.getString("exponent");
        password = RSAEncoder.RSAEncrypt(originalPassword, B64.b64tohex(modulus), B64.b64tohex(exponent));
        password = B64.hex2b64(password);
    }

    //登录
    private org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult login() throws Exception{
        connection = Jsoup.connect(url+ "/jwglxt/xtgl/login_slogin.html");
        connection.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");

        connection.data("csrftoken",csrftoken);
        connection.data("yhm",stuNum);
        connection.data("mm",password);
        connection.data("mm",password);
        connection.cookies(cookies).ignoreContentType(true)
                .method(Connection.Method.POST).execute();
        response = connection.execute();
        document = Jsoup.parse(response.body());
        if(document.getElementById("tips") == null){
            String data = getStudentInfo();
            logout();
            return new org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult(true,data);
        }else{
            String error = document.getElementById("tips").text();
            logout();
            return new ResponseResult(false,error);
        }
    }

    // 查询学生信息
    private String getStudentInfo() throws Exception {
        connection = Jsoup.connect(url+ "/jwglxt/xsxxxggl/xsxxwh_cxCkDgxsxx.html?gnmkdm=N100801&su="+ stuNum);
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        response = connection.cookies(cookies).ignoreContentType(true).execute();
        return response.body();
    }

    // 注销
    private void logout() throws Exception {
        connection = Jsoup.connect(url+ "/jwglxt/xsxxxggl/xsxxwh_cxCkDgxsxx.html?gnmkdm=N100801&su="+ stuNum);
        connection.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        response = connection.cookies(cookies).ignoreContentType(true).execute();
    }
}
