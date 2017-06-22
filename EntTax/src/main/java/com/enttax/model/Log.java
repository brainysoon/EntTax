package com.enttax.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Log")
public class Log {
    private String lId;

    private Date lTime;

    private String lMessage;

    public String getLId() {
        return lId;
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

    public void setLTime(Date lTime) {
        this.lTime = lTime;
    }

    public void setLMessage(String lMessage) {
        this.lMessage = lMessage;
    }


    @Override
    public String toString() {
        return "Log{" +
                "lId='" + lId + '\'' +
                ", lTime=" + lTime +
                ", lMessage='" + lMessage + '\'' +
                '}';
    }
}