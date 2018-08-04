import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetInfoFromJW {
    private final String path = "http://222.24.62.120";
    private final String loginPath = path + "/default2.aspx";
    private final String codeImgPath = path + "/CheckCode.aspx";
    private final String infoPath = path + "/xsgrxx.aspx?xh=";
    private CookieManager manager = new CookieManager();
    private User user;
    public byte[] getCheckCode(){
        CookieHandler.setDefault(manager);
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(codeImgPath).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream is = conn.getInputStream();
                byte[] buff = new byte[3000];
                int off = 0, len = 0;
                while((len = is.read(buff,off,buff.length-off)) > 0){
                    off += len;
                }
                //TODO 测试
                //System.out.println("验证码大小：" + off);
               // byte[] img = new byte[len];
               return Arrays.copyOf(buff,off);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            try {

            }catch (Exception ee){

            }
        }
        return null;

    }

    public String login(String xh ,String password ,String checkCode){
        String postData = "__VIEWSTATE=dDwxNTMxMDk5Mzc0Ozs%2BlYSKnsl%2FmKGQ7CKkWFJpv0btUa8%3D" +
                "&txtUserName="+xh+
                "&Textbox1=&TextBox2=" + password +
                "&txtSecretCode=" + checkCode +
                "&RadioButtonList1=%D1%A7%C9%FA" +
                "&Button1=&lbLanguage=&hidPdrs=&hidsc=";
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(loginPath).openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length",postData.length()+"");
            OutputStream os = conn.getOutputStream();
            os.write(postData.getBytes());
            os.flush();
            os.close();
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream is = conn.getInputStream();
                byte[] buff = new byte[10000];
                int off = 0, len = 0;
                while((len = is.read(buff,off,buff.length-off)) > 0){
                    off += len;
                }
                //TODO 测试完要删除
               // System.out.println("登录结果页面大小： "+off);
                String data = new String(buff,"GBK");//教务系统网站采用GB系编码
                if(data.indexOf("欢迎您",0) != -1){
                    return null;
                }else {
                    String reason = null;
                    String[] strings = data.split("<script language='javascript' defer>alert\\('");
                    try {
                        strings = strings[1].split("'\\);document.getElementById");
                        reason = strings[0];
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("可能是教务系统出现异常");
                        e.printStackTrace();
                    }

                    if(reason != null) System.out.println(reason);
                    return reason;
                }

            }else {
                System.out.println("请求教务系统网站失败，HTTP响应码："+responseCode);
            }
        }
        catch (Exception e){
            System.out.println("组织HTTP连接时抛出异常");
        }
        return null;
    }
    public User getUser(String xh){
        if(this.user != null && this.user.getXh() == xh) return this.user;
        CookieHandler.setDefault(manager);
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(infoPath + xh).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Referer",path);
            conn.connect();

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                InputStream is = conn.getInputStream();
                byte[] buff = new byte[24000];
                int off = 0, len = 0;
                while((len = is.read(buff,off,buff.length-off)) > 0){
                    off += len;
                }
                String data = new String(buff,"GBK");
                //TODO 测试
                //System.out.println("个人信息界面大小： "+off);
                User user = new User();
                Pattern p = Pattern.compile(
                        "<span id=\"xm\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_xb\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_csrq\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_lys\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_sfzh\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_xy\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_zymc\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_xzb\">(.*)</span>[\\s\\S]*" +
                                "<span id=\"lbl_dqszj\">(.*)</span>[\\s\\S]*");
                Matcher m = p.matcher(data);
                if(m.find()) {
                    user.setXh(xh);
                    user.setXm(m.group(1));
                    user.setXb(m.group(2));
                    user.setCsrq(m.group(3));
                    user.setLys(m.group(4));
                    user.setSfzh(m.group(5));
                    user.setXy(m.group(6));
                    user.setZymc(m.group(7));
                    user.setXzb(m.group(8));
                    user.setDqszj(m.group(9));
                    return user;
                }else {
                    System.out.println("结果匹配失败！");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public void wirteImg(byte[] data,String path){
        try {
            OutputStream os = new FileOutputStream(new File(path));
            os.write(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        GetInfoFromJW getInfoFromJW = new GetInfoFromJW();
    }

}
