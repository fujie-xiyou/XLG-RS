package org.xiyoulinux.recruitment.untils.getStuInfo;

public class ResponseResult {
    private boolean flag;
    private Object data;

    public ResponseResult(boolean flag, Object data){
        this.flag = flag;
        this.data = data;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
