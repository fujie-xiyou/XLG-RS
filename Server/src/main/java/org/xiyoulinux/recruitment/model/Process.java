package org.xiyoulinux.recruitment.model;

public class Process {
    private Integer processID;

    private Integer activityID;

    private Integer porder;

    private Integer pname;

    public Integer getProcessID() {
        return processID;
    }

    public void setProcessID(Integer processID) {
        this.processID = processID;
    }

    public Integer getActivityID() {
        return activityID;
    }

    public void setActivityID(Integer activityID) {
        this.activityID = activityID;
    }

    public Integer getPorder() {
        return porder;
    }

    public void setPorder(Integer porder) {
        this.porder = porder;
    }

    public Integer getPname() {
        return pname;
    }

    public void setPname(Integer pname) {
        this.pname = pname;
    }
}