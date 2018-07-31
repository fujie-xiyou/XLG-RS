package org.xiyoulinux.recruitment.model;

public class Sign {
    private Integer signID;

    private Integer joinID;

    private Integer processID;

    private String status;

    public Integer getSignID() {
        return signID;
    }

    public void setSignID(Integer signID) {
        this.signID = signID;
    }

    public Integer getJoinID() {
        return joinID;
    }

    public void setJoinID(Integer joinID) {
        this.joinID = joinID;
    }

    public Integer getProcessID() {
        return processID;
    }

    public void setProcessID(Integer processID) {
        this.processID = processID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}