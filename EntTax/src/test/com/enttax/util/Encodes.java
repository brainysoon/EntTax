package com.enttax.util;

import org.junit.Test;

import java.util.Date;

/**
 * Created by lcyanxi on 17-3-14.
 */
public class Encodes {
    @Test
     public void base64Test(){
         System.out.println(com.enttax.util.tools.Encodes.encodeBase64("李常"));
         System.out.println(new Date());

     }
}
