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
    public ResponseResult signUp(HttpServletRequest request){
        try {
            request.setCharacterEncoding("UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        Join join = new Join();
        String sno = request.getParameter("student_no");
        String real_name = request.getParameter("real_name");
        String admin_class = request.getParameter("admin_class");
        String passwd = request.getParameter("password");
        System.out.println("real_name = " + real_name);
        //String checkCode = request.getParameter("checkCode");
        String mobile = request.getParameter("mobile");

//        ConnectJWGL jw = (ConnectJWGL) request.getSession().getAttribute("JWGL");


//        if(jw == null) {
//            jw = new ConnectJWGL(sno,passwd);
//            request.getSession().setAttribute("JWGL",jw);
//        }
//        ConnectJWGL jw = new ConnectJWGL(sno,passwd);
//
//        org.xiyoulinux.recruitment.untils.getStuInfo.ResponseResult liMengResult = null;
//        try{
//            liMengResult = jw.init();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
 //       if(liMengResult.getFlag()){
        String syc = null;
        try {
            syc = "https://zypc.xupt.edu.cn/oauth/xyl2018sfyz?stuno="+sno+"&realname="+ URLEncoder.encode(real_name, "utf-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            System.out.println(syc);
            HttpURLConnection conn = (HttpURLConnection) new URL(syc).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream is = conn.getInputStream();
                byte[] buff = new byte[100];
                int off = 0, len = 0;
                while((len = is.read(buff,off,buff.length-off)) > 0){
                    off += len;
                }
                //TODO 测试完要删除
                // System.out.println("登录结果页面大小： "+off);
                String data = new String(buff,"GBK");//教务系统网站采用GB系编码
                if(data.indexOf("true",0) != -1){
                    //学号校验成功
                    if (joinDAO.selectByNo(sno) != null){
                        return new ResponseResult(new ResponseStatus("你已经报过名啦!"));
                    }
                    join.setProcess_id(0);
                    join.setIs_vaild(true);
                    join.setStudent_no(sno);
                    join.setCn_name(real_name);
                    join.setAdmin_class(admin_class);
                    if(joinDAO.insertSelective(join) > 0) {
                        return new ResponseResult(new ResponseStatus());
                    }else {
                        return new ResponseResult(new ResponseStatus("服务器异常"));
                    }
                }else {
                    return new ResponseResult(new ResponseStatus("哼，你不是"+real_name));
                }

            }else {
                System.out.println("连接失败，HTTP响应码："+responseCode);
            }
        }
        catch (Exception e){
            System.out.println("组织HTTP连接时抛出异常");
        }
            //当学号密码校验通过后才能得知是否已经报过名
//            if (joinDAO.selectByNo(sno) != null){
//                return new ResponseResult(new ResponseStatus("你已经报过名啦!"));
//            }
//            System.out.println(liMengResult.getData());
//            JSONObject jsonObject = JSON.parseObject(liMengResult.getData().toString());
//            join.setStudent_no(jsonObject.getString("xh_id"));
//            join.setSex(jsonObject.getString("xbm").equals("男")? "boy":"girl");
//            join.setCollege(jsonObject.getString("jg_id"));
//            join.setAdmin_class(jsonObject.getString("bh_id"));
//            join.setDept(jsonObject.getString("zszyh_id"));
//            join.setCn_name(jsonObject.getString("xm"));
//            join.setBirthday(LocalDate.parse(jsonObject.getString("csrq"),
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            join.setMobile(mobile);
//            join.setPasswd(Md5Until.getMd5(passwd));
//            join.setProcess_id(0);
//            join.setIs_vaild(true);
//            join.setStudent_no(sno);
//            join.setCn_name(real_name);
//            join.setAdmin_class(admin_class);
//            if(joinDAO.insertSelective(join) > 0) {
//                return new ResponseResult(new ResponseStatus());
//            }
//        }else {
//            return new ResponseResult(new ResponseStatus((String)liMengResult.getData()));
//        }


        return new ResponseResult(new ResponseStatus("服务器异常..   " +
                "" +
                "" +
                ""));
    }
}
