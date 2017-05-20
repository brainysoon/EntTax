package com.enttax.data.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by brainy on 17-5-5.
 */
public class RandomUtil {

    /**
     * 随机生成 BId 22位  前17位跟日期有关  后5位 随机数
     */
    private static final SimpleDateFormat sdfBId = new SimpleDateFormat("YYYYMMddHHmmssSSS");

    /**
     * 进项数据 和 销项数据  类型
     */
    public static final String TAX_IN = "进项数据";
    public static final String TAX_OUT = "销项数据";
    public static final String PROJECT_PRE_STR = "name";

    /**
     * 每个月的最大项目数  100000
     */
    public static final int MAX_PROJECT = 1;
    public static final int MAX_MONTH = 8;

    private static final Random random = new Random();

    /**
     * @return 随机生成 22位 BId
     */
    public static String generateBId() {

        String preBId = sdfBId.format(new Date());

        StringBuffer subBId = new StringBuffer();

        for (int i = 0; i < 5; i++) {

            subBId.append(Math.abs(random.nextInt()) % 10);
        }

        return preBId + subBId.toString();
    }

    /**
     * @return 生成数据  单位万元
     */
    public static Double generateBPrice() {

        return random.nextDouble();
    }
}
