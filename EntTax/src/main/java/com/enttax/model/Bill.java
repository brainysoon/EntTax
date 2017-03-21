package com.enttax.model;

import org.apache.ibatis.type.Alias;

import java.util.Date;
@Alias("Bill")
public class Bill {
    private String bid;

    private String btype;

    private Integer bnum;

    private Double bprice;

    private String bmonth;

    private String btitle;

    private Date bdate;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid == null ? null : bid.trim();
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype == null ? null : btype.trim();
    }

    public Integer getBnum() {
        return bnum;
    }

    public void setBnum(Integer bnum) {
        this.bnum = bnum;
    }

    public Double getBprice() {
        return bprice;
    }

    public void setBprice(Double bprice) {
        this.bprice = bprice;
    }

    public String getBmonth() {
        return bmonth;
    }

    public void setBmonth(String bmonth) {
        this.bmonth = bmonth == null ? null : bmonth.trim();
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle == null ? null : btitle.trim();
    }

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }
}