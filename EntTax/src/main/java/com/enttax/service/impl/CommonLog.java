package com.enttax.service.impl;

import com.enttax.model.Staff;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolRandoms;
import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * Created by lcyanxi on 17-6-20.
 */
public class CommonLog {

    /**
     * 生成系统日志消息
     * @param message
     * @return
     */
     public static  com.enttax.model.Log createLogMessage(String message ,Session session){

        Staff currentStaff=(Staff) session.getAttribute(ConstantStr.STAFFINFO);
        com.enttax.model.Log log=new com.enttax.model.Log();
        log.setLId(ToolRandoms.randomId20());
        log.setLTime(new Date());
        log.setLMessage(currentStaff.getSName()+message);
        return log;
    }
}
