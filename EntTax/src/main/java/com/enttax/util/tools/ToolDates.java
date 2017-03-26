package com.enttax.util.tools;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by lcyanxi on 17-3-15.
 */
public class ToolDates {

    public static String formatDate(Date date, Object... pattern) {
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 用分，秒，毫秒时间戳产生８位随机数
     * @return
     * @throws ParseException
     */
    public static String getDate8Num() {
        Date d = new Date();//获取时间
        SimpleDateFormat sdf=new SimpleDateFormat("mmssSSS");
        String ymd = sdf.format(d)+new Random().nextInt(10);
        return ymd ;
    }

    /**
     * 产生15为随机数
     * @return
     */
    public static String getDate15Num() {
        Date d = new Date();//获取时间
        SimpleDateFormat sdf=new SimpleDateFormat("ddHHmmssSSS");
        String ymd = sdf.format(d)+new Random().nextInt(10000);
        return ymd ;
    }

}
