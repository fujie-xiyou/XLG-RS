package org.xiyoulinux.recruitment.untils;

import java.util.List;


public class ResponseResult<T> {
    private ResponseStatus status;
    private T result;

    public ResponseResult(ResponseStatus status, T result) {
        this.status = status;
        this.result = result;
    }
    public ResponseResult(ResponseStatus status){
        this(status,null);
    }
    public ResponseResult(T result){
        this(new ResponseStatus(),result);
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
