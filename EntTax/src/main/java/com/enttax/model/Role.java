package com.enttax.model;

import java.util.Date;

public class Role {
    private String rid;

    private String rname;

    private Date rupdatetime;

    private Short rmark;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid == null ? null : rid.trim();
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname == null ? null : rname.trim();
    }

    public Date getRupdatetime() {
        return rupdatetime;
    }

    public void setRupdatetime(Date rupdatetime) {
        this.rupdatetime = rupdatetime;
    }

    public Short getRmark() {
        return rmark;
    }

    public void setRmark(Short rmark) {
        this.rmark = rmark;
    }
}