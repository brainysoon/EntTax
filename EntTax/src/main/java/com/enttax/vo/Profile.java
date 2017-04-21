package com.enttax.vo;

import java.util.Date;

/**
 * Created by lcyanxi on 17-4-21.
 */
public class Profile {
    private String sname;
    private boolean ssex;
    private Date sbirthday;
    private String saddress;

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public boolean isSsex() {
        return ssex;
    }

    public void setSsex(boolean ssex) {
        this.ssex = ssex;
    }

    public Date getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "sname='" + sname + '\'' +
                ", ssex='" + ssex + '\'' +
                ", sbirthday='" + sbirthday + '\'' +
                ", saddress='" + saddress + '\'' +
                '}';
    }
}
