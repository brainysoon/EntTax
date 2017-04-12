package com.enttax.util.tools;


import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**  密码加密
 * Created by lcyanxi on 17-3-14.
 */
public class Encodes {

    private static final String DEFAULT_URL_ENCODING = "UTF-8";


    public static String encodeBase64(String input){
        try {
            return new String(Base64.encodeBase64(input.getBytes(DEFAULT_URL_ENCODING)));
        }catch (UnsupportedEncodingException e){
            return "";
        }
    }
}
