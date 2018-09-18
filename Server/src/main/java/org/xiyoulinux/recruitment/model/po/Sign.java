package org.xiyoulinux.recruitment.model.po;

public class Sign {
    private Integer id;

    private Integer join_id;

    private Integer process_id;

    private String sign_status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJoin_id() {
        return join_id;
    }

    public void setJoin_id(Integer join_id) {
        this.join_id = join_id;
    }

    public Integer getProcess_id() {
        return process_id;
    }

    public void setProcess_id(Integer process_id) {
        this.process_id = process_id;
    }

    public String getSign_status() {
        return sign_status;
    }

    public void setSign_status(String sign_status) {
        this.sign_status = sign_status;
    }
}