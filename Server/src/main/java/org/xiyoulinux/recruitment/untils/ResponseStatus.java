package org.xiyoulinux.recruitment.untils;

import java.util.List;

public class ResponseStatus{
    private boolean status;//响应状态
    private String[] reasons;//具体错误原因

    //方便起见，无参构造方法默认为请求处理成功
    public ResponseStatus() {
        this(true,null);
    }
    
    //直接使用字符串或字符串数组的构造器响应状态将为false
    public ResponseStatus(String... reasons){
        this(false,reasons);
    }

    public ResponseStatus(boolean status, String... reasons) {
        this.status = status;
        this.reasons = reasons;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String[] getReasons() {
        return reasons;
    }

    public void setReasons(String[] reasons) {
        this.reasons = reasons;
    }
}
