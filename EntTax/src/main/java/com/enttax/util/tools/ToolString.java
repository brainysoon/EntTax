package com.enttax.util.tools;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.trim;

/**
 * Created by lcyanxi on 17-3-15.
 */
public class ToolString {

    /**
     *  判断是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     *   匹配email是否合法
     */
    public final static String regExp_email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

    /**
     *  验证是否是手机号
     * @param queryString
     * @return
     */
    public static boolean isPhoneNumber(String queryString) {
        return (queryString.length() == 11 && hasDigital(queryString) == 11);
    }

    /**
     * 验证是否是邮箱
     * @param content
     * @return
     */
    public static boolean isEmail(String content) {
        String regExp_email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
        Pattern pattern = Pattern.compile(regExp_email);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }
    /**
     * 转换为Double类型
     */
    public static Double toDouble(Object val){
        if (val == null){
            return 0D;
        }
        try {
            return Double.valueOf(trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(Object val){
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(Object val){
        return toLong(val).intValue();
    }


    private static int hasDigital(String queryString) {
        int count = 0;
        String regEx = "[0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(queryString);
        while (m.find()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                count++;
            }
        }
        return count;
    }
}
