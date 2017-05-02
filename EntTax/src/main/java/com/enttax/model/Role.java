package com.enttax.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Role")
public class Role {
    private String rId;

    private String rName;

    private String rLabel;

    private Date rUpdateTime;

    private Integer rMark;

    public String getRId() {
        return rId;
    }

    public String getRName() {
        return rName;
    }

    public String getRLabel() {
        return rLabel;
    }

    public Date getRUpdateTime() {
        return rUpdateTime;
    }

    public Integer getRMark() {
        return rMark;
    }

    public void setRId(String rId) {
        this.rId = rId;
    }

    public void setRName(String rName) {
        this.rName = rName;
    }

    public void setRLabel(String rLabel) {
        this.rLabel = rLabel;
    }

    public void setRUpdateTime(Date rUpdateTime) {
        this.rUpdateTime = rUpdateTime;
    }

    public void setRMark(Integer rMark) {
        this.rMark = rMark;
    }
}