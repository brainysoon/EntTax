package com.enttax.util.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by lcyanxi on 17-3-14.
 */
public class ToolRandoms {
    public static final Random random = new Random();

    private static final SimpleDateFormat yearDateFormat = new SimpleDateFormat("YYYY");
    private static final SimpleDateFormat longDateFormat = new SimpleDateFormat("YYYYMMddHHmmssSSS");
    private static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("ssSSS");

    /**
     * 生成一个8位 数的随机数  前4位年  后四位 随机数
     *
     * @return
     */
    public static String randomCode8() {
        String codeStr = yearDateFormat.format(new Date());

        for (int i = 0; i < 4; i++) {

            codeStr += Math.abs(random.nextInt()) % 10;
        }

        return codeStr;
    }

    /**
     * 随机生成一个邮箱 用于测试
     *
     * @return
     */
    public static String randomEmail() {

        return longDateFormat.format(new Date()) + "@163.com";
    }

    /**
     * 随机生成一个手机号码用于测试
     *
     * @return
     */
    public static String randomPhoneNum() {

        return "131669" + shortDateFormat.format(new Date());
    }

    public static String randomId() {

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYHH");

        String str = sdf.format(new Date());

        System.out.println(str);

        return str;
    }

    /**
     * 为测试 生成Bill Id  22 位字符串
     *
     * @return
     */
    public static String randomBillId() {

        String billIdStr = longDateFormat.format(new Date());

        for (int i = 0; i < 5; i++) {

            billIdStr += Math.abs(random.nextInt()) % 10;
        }

        return billIdStr;
    }

    /**
     * 为 测试生成四位的id
     *
     * @return
     */
    public static String randomId4() {

        StringBuffer codeStr4 = new StringBuffer();

        for (int i = 0; i < 4; i++) {

            codeStr4.append(Math.abs(random.nextInt()) % 10);
        }

        return codeStr4.toString();
    }

    /**
     * 为测试 生成 Id  20 位字符串
     *
     * @return
     */
    public static String randomId20() {

        String idStr = longDateFormat.format(new Date());

        for (int i = 0; i < 3; i++) {

            idStr += Math.abs(random.nextInt()) % 10;
        }

        return idStr;
    }
}
