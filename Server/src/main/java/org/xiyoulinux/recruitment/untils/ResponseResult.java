package org.xiyoulinux.recruitment.untils;

/*
* 状态码
* 0  一般错误
* 1  成功
* 2  已经报报名  指示前端跳转到个人信息页面
* 3  在报名提交手机号的时候发现没有notSign这个session  说明不是通过前一个学号校验界面进入的
*
* */

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
    public ResponseResult(int status){
        this(status,null);
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
