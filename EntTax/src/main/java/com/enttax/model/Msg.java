package com.enttax.model;

import java.util.Date;

/**
 * Created by brainy on 17-5-23.
 */
public class Msg {

    private String mId;

    private String toSId;

    private String fromSId;

    private Integer mRead;

    private String mContent;

    private Date mDate;

    private Integer mMark;

    public String getMId() {
        return mId;
    }

    public String getToSId() {
        return toSId;
    }

    public String getFromSId() {
        return fromSId;
    }

    public Integer getMRead() {
        return mRead;
    }

    public String getMContent() {
        return mContent;
    }

    public Date getMDate() {
        return mDate;
    }

    public Integer getMMark() {
        return mMark;
    }

    public void setMId(String mId) {
        this.mId = mId;
    }

    public void setToSId(String toSId) {
        this.toSId = toSId;
    }

    public void setFromSId(String fromSId) {
        this.fromSId = fromSId;
    }

    public void setMRead(Integer mRead) {
        this.mRead = mRead;
    }

    public void setMContent(String mContent) {
        this.mContent = mContent;
    }

    public void setMDate(Date mDate) {
        this.mDate = mDate;
    }

    public void setMMark(Integer mMark) {
        this.mMark = mMark;
    }
}
