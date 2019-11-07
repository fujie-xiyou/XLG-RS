package org.xiyoulinux.recruitment.model.vo;

import java.time.LocalDate;

public class SmsJoin {
    private Integer id;

    private String student_no;

    private Integer process_id;

    private String cn_name;

    private String admin_class;

    private String mobile;

    private String time_segment;

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

    public String getCn_name() {
        return cn_name;
    }

    public void setCn_name(String cn_name) {
        this.cn_name = cn_name;
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

    public String getTime_segment() {
        return time_segment;
    }

    public void setTime_segment(String time_segment) {
        this.time_segment = time_segment;
    }
}
