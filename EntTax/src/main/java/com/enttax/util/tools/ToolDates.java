package com.enttax.util.tools;

import com.enttax.model.Bill;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by lcyanxi on 17-3-15.
 */
public class ToolDates {

    private static final Logger logger = Logger.getLogger(ToolDates.class);

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy");

    /**
     * 将date日期转换为字符串的日期
     *
     * @param date
     * @param pattern
     * @return
     */
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
     * 将字符串的日期转换为date类型的日期
     *
     * @param date 当前日期
     * @return date
     */
    public static Date parseDateStr(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(date);
        } catch (ParseException e) {
            logger.info("ToolDateTime.parse异常：date值" + date);
            return null;
        }
    }

    /**
     * 用分，秒，毫秒时间戳产生８位随机数
     *
     * @return
     * @throws ParseException
     */
    public static String getDate8Num() {
        Date d = new Date();//获取时间
        SimpleDateFormat sdf = new SimpleDateFormat("mmssSSS");
        String ymd = sdf.format(d) + new Random().nextInt(10);
        return ymd;
    }

    /**
     * 产生15为随机数
     *
     * @return
     */
    public static String getDate15Num() {
        Date d = new Date();//获取时间
        SimpleDateFormat sdf = new SimpleDateFormat("ddHHmmssSSS");
        String ymd = sdf.format(d) + new Random().nextInt(10000);
        return ymd;
    }

    public static Bill fixDateBaseMonth(Bill bill) {

        //未经折算
        int month = bill.getBMonth();


        //折算后的月份
        int sub_month = month % 12;

        //折算的年份
        int year = month / 12;


        if (sub_month == 0) {

            year--;
            sub_month = 12;
        }

        int sub_year = bill.getBUpdateTime().getYear() - year;

        //更新
        bill.setBMonth(sub_month);

        Date date = bill.getBUpdateTime();
        date.setYear(sub_year);
        bill.setBUpdateTime(date);

        return bill;
    }

    /**
     * @param month
     * @return
     */
    public static int[][] getPreSixMonth(int month, int count) {

        int[][] months = new int[count][2];

        int year = Integer.parseInt(sdf.format(new Date()));

        for (int i = count - 1; i >= 0; i--) {

            month--;
            if (month == 0) {
                month = 12;
                year--;
            }

            months[i][0] = month;
            months[i][1] = year;
        }

        return months;
    }
}
