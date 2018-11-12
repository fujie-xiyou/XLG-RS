package org.xiyoulinux.recruitment.untils;



public class ResponseResult<T> {
    private int status;
    private T result;

    public ResponseResult(int status, T result) {
        this.status = status;
        this.result = result;
    }
    public ResponseResult(){
        this(null);
    }

    public ResponseResult(T result){
        this(1,result);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
