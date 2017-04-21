package com.enttax.util.tools;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lcyanxi on 17-4-21.
 */
public enum  EnumSex {
    male, female;

    public static String parseSex(int sex){
        EnumSex[] enumSex = values();
        switch (enumSex[sex]){
            case male:
                return "男";
            case female:
                return "女";
            default:
                return StringUtils.EMPTY;
        }
    }
}
