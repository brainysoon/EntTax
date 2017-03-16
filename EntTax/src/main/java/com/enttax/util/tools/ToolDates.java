package com.enttax.util.tools;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String getSystemDateByyymmdd() throws ParseException {
        Date d = new Date();//获取时间

        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
        String ymd = sdf.format(d);
        return ymd ;
    }
}
