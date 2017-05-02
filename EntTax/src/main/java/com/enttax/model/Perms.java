package com.enttax.model;

import org.apache.ibatis.type.Alias;

@Alias("Perms")
public class Perms {
    private String pId;

    private String pName;

    private String pLabel;

    private Integer pType;

    private Integer pMark;

    public String getPId() {
        return pId;
    }

    public String getPName() {
        return pName;
    }

    public String getPLabel() {
        return pLabel;
    }

    public Integer getPType() {
        return pType;
    }

    public Integer getPMark() {
        return pMark;
    }

    public void setPId(String pId) {
        this.pId = pId;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public void setPLabel(String pLabel) {
        this.pLabel = pLabel;
    }

    public void setPType(Integer pType) {
        this.pType = pType;
    }

    public void setPMark(Integer pMark) {
        this.pMark = pMark;
    }
}