package org.xiyoulinux.recruitment.model;

import java.time.LocalDate;

public class Join {
    private Integer id;

    private String student_no;

    private Integer process_id;

    private Boolean is_vaild;

    private String cn_name;

    private String passwd;

    private String sex;

    private LocalDate birthday;

    private String province;

    private String college;

    private String dept;

    private String admin_class;

    private String mobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent_no() {
        return student_no;
    }

    public void setStudent_no(String student_no) {
        this.student_no = student_no;
    }

    public Integer getProcess_id() {
        return process_id;
    }

    public void setProcess_id(Integer process_id) {
        this.process_id = process_id;
    }

    public Boolean getIs_vaild() {
        return is_vaild;
    }

    public void setIs_vaild(Boolean is_vaild) {
        this.is_vaild = is_vaild;
    }

    public String getCn_name() {
        return cn_name;
    }

    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getAdmin_class() {
        return admin_class;
    }

    public void setAdmin_class(String admin_class) {
        this.admin_class = admin_class;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}