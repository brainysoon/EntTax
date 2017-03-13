package com.enttax.model;

public class Permission {
    private String pid;

    private String pname;

    private Integer ptype;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }
}