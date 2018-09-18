package org.xiyoulinux.recruitment.model.vo;

public class Pagination {
    private int total;
    private Object rows;

    public Pagination() {}

    public Pagination(int total, Object rows) {
        this.total = total;
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }
}
