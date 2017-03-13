package com.enttax.model;

import java.util.Date;

public class Log {
    private String lid;

    private String sid;

    private String bid;

    private Date ltime;

    private String lmessage;

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid == null ? null : lid.trim();
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid == null ? null : sid.trim();
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public Date getLtime() {
        return ltime;
    }

    public void setLtime(Date ltime) {
        this.ltime = ltime;
    }

    public String getLmessage() {
        return lmessage;
    }

    public void setLmessage(String lmessage) {
        this.lmessage = lmessage == null ? null : lmessage.trim();
    }
}