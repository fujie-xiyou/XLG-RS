package org.xiyoulinux.recruitment.untils;

import java.util.List;

public class ResponseStatus {
    /* 响应状态码： 1代表成功，0代表失败，2代表登录状态失效，
     * 前端一旦接收到状态为2的请求 就立即跳转到登录界面
     */
    private int statusCode;//响应状态
    private String[] reasons;//具体错误原因

    //方便起见，无参构造方法默认为请求处理成功
    public ResponseStatus() {
        this(1);
    }

    //直接使用字符串或字符串数组的构造器响应状态将为500
    public ResponseStatus(String... reasons) {
        this(0, reasons);
    }

    public ResponseStatus(int statusCode, String... reasons) {
        this.statusCode = statusCode;
        this.reasons = reasons;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String[] getReasons() {
        return reasons;
    }

    public void setReasons(String[] reasons) {
        this.reasons = reasons;
    }
}
