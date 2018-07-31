package org.xiyoulinux.recruitment.model;

import org.xiyoulinux.recruitment.model.enums.SEX;
import org.apache.ibatis.type.Alias;

import java.util.Date;
@Alias("Join")
public class Join {
    private int joinId; // 报名编号
    private String Sno; // 学号
    private int processID; // 流程编号
    private boolean isVaild; // false代表被淘汰
    private String Sname; // 姓名
    private String Spasswd; // 教务系统密码
    private SEX Ssex;
    private Date Sbirthday;
    private String Sprovince; // 省份
    private String Scoll; // 学院
    private String Sdept; // 专业名称
    private String Sclass; // 行政班
    private String Sphone; // 手机号

    public int getJoinId() {
        return joinId;
    }

    public void setJoinId(int joinId) {
        this.joinId = joinId;
    }

    public String getSno() {
        return Sno;
    }

    public void setSno(String sno) {
        Sno = sno;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }

    public boolean isVaild() {
        return isVaild;
    }

    public void setVaild(boolean vaild) {
        isVaild = vaild;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSpasswd() {
        return Spasswd;
    }

    public void setSpasswd(String spasswd) {
        Spasswd = spasswd;
    }

    public SEX getSsex() {
        return Ssex;
    }

    public void setSsex(SEX ssex) {
        Ssex = ssex;
    }

    public Date getSbirthday() {
        return Sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        Sbirthday = sbirthday;
    }

    public String getSprovince() {
        return Sprovince;
    }

    public void setSprovince(String sprovince) {
        Sprovince = sprovince;
    }

    public String getScoll() {
        return Scoll;
    }

    public void setScoll(String scoll) {
        Scoll = scoll;
    }

    public String getSdept() {
        return Sdept;
    }

    public void setSdept(String sdept) {
        Sdept = sdept;
    }

    public String getSclass() {
        return Sclass;
    }

    public void setSclass(String sclass) {
        Sclass = sclass;
    }

    public String getSphone() {
        return Sphone;
    }

    public void setSphone(String sphone) {
        Sphone = sphone;
    }
}
