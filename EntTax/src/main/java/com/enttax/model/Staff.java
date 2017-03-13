package com.enttax.model;

import java.util.Date;

public class Staff {
    private String sid;

    private String sname;

    private String spassword;

    private String semail;

    private String sphone;

    private Boolean ssex;

    private Date sbirthday;

    private Date senter;

    private Short smark;

    private String saddress;

    private String savator;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword == null ? null : spassword.trim();
    }

    public String getSemail() {
        return semail;
    }

    public void setSemail(String semail) {
        this.semail = semail == null ? null : semail.trim();
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone == null ? null : sphone.trim();
    }

    public Boolean getSsex() {
        return ssex;
    }

    public void setSsex(Boolean ssex) {
        this.ssex = ssex;
    }

    public Date getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public Date getSenter() {
        return senter;
    }

    public void setSenter(Date senter) {
        this.senter = senter;
    }

    public Short getSmark() {
        return smark;
    }

    public void setSmark(Short smark) {
        this.smark = smark;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress == null ? null : saddress.trim();
    }

    public String getSavator() {
        return savator;
    }

    public void setSavator(String savator) {
        this.savator = savator == null ? null : savator.trim();
    }
}