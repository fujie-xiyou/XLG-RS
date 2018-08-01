package org.xiyoulinux.recruitment.model;

import java.util.Date;

public class Join {
    private Integer joinID;

    private String sno;

    private Integer processID;

    private Boolean is_vaild;

    private String sname;

    private String spasswd;

    private String ssex;

    private Date sbirthday;

    private String sprovince;

    private String scoll;

    private String sdept;

    private String sclass;

    private String sphone;

    public Integer getJoinID() {
        return joinID;
    }

    public void setJoinID(Integer joinID) {
        this.joinID = joinID;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public Integer getProcessID() {
        return processID;
    }

    public void setProcessID(Integer processID) {
        this.processID = processID;
    }

    public Boolean getIs_vaild() {
        return is_vaild;
    }

    public void setIs_vaild(Boolean is_vaild) {
        this.is_vaild = is_vaild;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpasswd() {
        return spasswd;
    }

    public void setSpasswd(String spasswd) {
        this.spasswd = spasswd;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Date getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSprovince() {
        return sprovince;
    }

    public void setSprovince(String sprovince) {
        this.sprovince = sprovince;
    }

    public String getScoll() {
        return scoll;
    }

    public void setScoll(String scoll) {
        this.scoll = scoll;
    }

    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }
}