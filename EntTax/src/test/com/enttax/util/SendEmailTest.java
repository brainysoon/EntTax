package com.enttax.util;

import com.enttax.util.tools.SendEmail;
import org.junit.Test;

/**
 * Created by lcyanxi on 17-4-18.
 */
public class SendEmailTest {
//    804480875@qq.com
    @Test
    public void sendEmailTest(){
        SendEmail.sendEmail("804480875@qq.com");
        System.out.println("sendEmail success!!!");
    }

}
