package com.enttax.util;

import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolRandoms;
import com.enttax.util.tools.ToolString;
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
         System.out.println(Long.parseLong(ToolDates.getTime()));

     }

    @Test
    public void testId()throws Exception{
        System.out.println(ToolString.isEmail("5892601@qq.com"));
        for(int i=0;i<10;i++){
            System.out.println("-------------------------");
            ToolDates.getDate8Num();
            System.out.println("-------------------------");
        }
        System.out.println("-----------15位随机数--------------");
        System.out.println(ToolDates.getDate15Num());
        ToolRandoms.randomId();
    }


    @Test
    public void parseDate(){
        System.out.println(ToolDates.parseDateStr("2014-7-10"));
    }



}
