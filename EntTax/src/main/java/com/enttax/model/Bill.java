package com.enttax.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Bill")
public class Bill {
    private String bId;

    private String bType;

    private String bName;

    private Integer bMonth;

    private Double bPrice;

    private Date bUpdateTime;

    private Integer bMark;

    public String getBbId() {
        return bId;
    }

    public String getBType() {
        return bType;
    }

    public String getBName() {
        return bName;
    }

    public Integer getBMonth() {
        return bMonth;
    }

    public Double getBPrice() {
        return bPrice;
    }

    public Date getBUpdateTime() {
        return bUpdateTime;
    }

    public Integer getBMark() {
        return bMark;
    }

    public void setBId(String bId) {
        this.bId = bId;
    }

    public void setBType(String bType) {
        this.bType = bType;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public void setBMonth(Integer bMonth) {
        this.bMonth = bMonth;
    }

    public void setBPrice(Double bPrice) {
        this.bPrice = bPrice;
    }

    public void setBUpdateTime(Date bUpdateTime) {
        this.bUpdateTime = bUpdateTime;
    }

    public void setBMark(Integer bMark) {
        this.bMark = bMark;
    }
}