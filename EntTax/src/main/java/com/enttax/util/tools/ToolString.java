package com.enttax.util.tools;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
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
     * 常用正则表达式：匹配非负浮点数（正浮点数 + 0）
     */
    public final static String regExp_float_1 = "^\\d+(\\.\\d+)?$";

    /**
     * 常用正则表达式：匹配正浮点数
     */
    public final static String regExp_float_2 = "^(([0-9]+\\.[0-9]*[1-9][0-9]*) ?([0-9]*[1-9][0-9]*\\.[0-9]+) ?([0-9]*[1-9][0-9]*))$";



    /**
     * 验证字符串是否匹配指定正则表达式
     *
     * @param content 目标字符串
     * @param regExp 正则
     * @return boolean
     */
    public static boolean regExpVali(String content, String regExp) {
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }

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

    /**
     * 转换为String类型
     * @param str
     * @return
     */
    public static String toString(Object str){
        String returnValue = "";
        try {
            returnValue = new BigDecimal(String.valueOf(str)).toString();
            if(StringUtils.endsWith(returnValue, ".0")) {
                returnValue = StringUtils.removeEnd(returnValue, ".0");
            }
        } catch (Exception e) {
            returnValue = ObjectUtils.toString(str);
        }
        return returnValue;
    }


    /**
     * 辅助校验是否为手机号
     * @param queryString
     * @return
     */
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
