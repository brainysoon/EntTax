package com.enttax.vo;

import com.enttax.model.Msg;
import org.apache.ibatis.type.Alias;

/**
 * Created by brainy on 17-5-31.
 */
@Alias("MsgInfo")
public class MsgInfo extends Msg {

    private String fromName;

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
