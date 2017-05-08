package com.enttax.model;

import com.sun.istack.internal.NotNull;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Alias("Staff")
public class Staff {
    private String sId;

    @NotNull
    private String sName;

    @NotNull
    private String sPassword;

    private String sSalt;

    private String sEmail;

    private String sPhone;

    private Boolean sSex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sBirthday;

    private Date sEnter;

    private Integer sMark;

    private String sAddress;

    private String sAvatar;

    public String getSId() {
        return sId;
    }

    public String getSName() {
        return sName;
    }

    public String getSPassword() {
        return sPassword;
    }

    public String getSSalt() {
        return sSalt;
    }

    public String getSEmail() {
        return sEmail;
    }

    public String getSPhone() {
        return sPhone;
    }

    public Boolean getSSex() {
        return sSex;
    }

    public Date getSBirthday() {
        return sBirthday;
    }

    public Date getSEnter() {
        return sEnter;
    }

    public Integer getSMark() {
        return sMark;
    }

    public String getSAddress() {
        return sAddress;
    }

    public String getSAvatar() {
        return sAvatar;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public void setSPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public void setSSalt(String sSalt) {
        this.sSalt = sSalt;
    }

    public void setSEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public void setSPhone(String sPhone) {
        this.sPhone = sPhone;
    }

    public void setSSex(Boolean sSex) {
        this.sSex = sSex;
    }

    public void setSBirthday(Date sBirthday) {
        this.sBirthday = sBirthday;
    }

    public void setSEnter(Date sEnter) {
        this.sEnter = sEnter;
    }

    public void setSMark(Integer sMark) {
        this.sMark = sMark;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public void setSAvatar(String sAvatar) {
        this.sAvatar = sAvatar;
    }
}