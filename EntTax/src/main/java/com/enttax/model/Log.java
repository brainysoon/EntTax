package com.enttax.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Log")
public class Log {
    private String lId;

    private String sId;

    private String bId;

    private Date lTime;

    private String lMessage;

    public String getLId() {
        return lId;
    }

    public String getSId() {
        return sId;
    }

    public String getBId() {
        return bId;
    }

    public Date getLTime() {
        return lTime;
    }

    public String getLMessage() {
        return lMessage;
    }

    public void setLId(String lId) {
        this.lId = lId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public void setLTime(Date lTime) {
        this.lTime = lTime;
    }

    public void setLMessage(String lMessage) {
        this.lMessage = lMessage;
    }
}