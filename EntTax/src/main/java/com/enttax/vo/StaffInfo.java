package com.enttax.vo;

import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by lcyanxi on 17-5-5.
 */
@Alias("StaffInfo")
public class StaffInfo {
    private String sId;
    private String sName;
    private String sPhone;
    private String sAvatar;
    private Date sEnter;
    private String rName;
    private String sEmail;

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public String getsAvatar() {
        return sAvatar;
    }

    public void setsAvatar(String sAvatar) {
        this.sAvatar = sAvatar;
    }

    public Date getsEnter() {
        return sEnter;
    }

    public void setsEnter(Date sEnter) {
        this.sEnter = sEnter;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    @Override
    public String toString() {
        return "StaffInfo{" +
                "sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", sPhone='" + sPhone + '\'' +
                ", sAvatar='" + sAvatar + '\'' +
                ", sEnter=" + sEnter +
                ", rName='" + rName + '\'' +
                '}';
    }
}
