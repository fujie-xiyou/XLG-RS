package org.xiyoulinux.recruitment.model.vo;

public class Page {

    private int limit;      //每页显示数量
    private int offset;     //sql语句起始索引

    public int getLimit() {
        return limit;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
}

