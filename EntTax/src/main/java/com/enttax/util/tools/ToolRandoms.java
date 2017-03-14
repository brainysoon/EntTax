package com.enttax.util.tools;

import java.util.Random;

/**
 * Created by lcyanxi on 17-3-14.
 */
public class ToolRandoms {
    private static final Random random = new Random();

    /**
     * 生成一个8位数的随机数
     * @return
     */
    public static String randomCode8() {
        String codeStr;
        int codeInt = random.nextInt(100000000);
        codeStr = String.valueOf(codeInt);
        int length = codeStr.length();
        for (; length < 8; length ++) {
            codeInt = codeInt * 10;
        }
        codeStr = String.valueOf(codeInt);
        return codeStr;
    }


}
