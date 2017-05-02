package com.enttax.vo;

/**
 * Created by lcyanxi on 17-4-21.
 */
public class Profile {
    private String sName;
    private String sSex;
    private String sBirthday;
    private String sAddress;

    public String getSName() {
        return sName;
    }

    public String getSSex() {
        return sSex;
    }

    public String getSBirthday() {
        return sBirthday;
    }

    public String getSAddress() {
        return sAddress;
    }

    public void setSName(String sName) {
        this.sName = sName;
    }

    public void setSSex(String sSex) {
        this.sSex = sSex;
    }

    public void setSBirthday(String sBirthday) {
        this.sBirthday = sBirthday;
    }

    public void setSAddress(String sAddress) {
        this.sAddress = sAddress;
    }
}
