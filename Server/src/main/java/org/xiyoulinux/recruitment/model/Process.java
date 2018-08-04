package org.xiyoulinux.recruitment.model;

public class Process {
    private Integer id;

    private Integer activity_id;

    private Integer order;

    private Integer process_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Integer activity_id) {
        this.activity_id = activity_id;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getProcess_name() {
        return process_name;
    }

    public void setProcess_name(Integer process_name) {
        this.process_name = process_name;
    }
}