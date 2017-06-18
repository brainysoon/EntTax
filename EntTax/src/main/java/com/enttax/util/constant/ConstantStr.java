package com.enttax.util.constant;

/**
 * Created by lcyanxi on 17-3-15.
 */
public interface ConstantStr {
    /**
     * 字符串0
     */
    String str_zero = "0";
    /**
     * 字符串1
     */
    String str_one = "1";
    /**
     * 字符串2
     */
    String str_two = "2";
    /**
     * 字符串3
     */
    String str_three = "3";

    /**
     * 员工id号
     */
    String SID = "sid";

    /**
     * 图片上传的域名前缀
     */
    String IMAGE_UPLOAD_DOMAIN = "http://192.168.0.104/";

    /**
     * 图片上传路径地址
     */
    String IMAGEUPLOADPATH = "/var/www/avatar/";

    /**
     * 生成excel模板的绝对地址
     */
    String EXCEL_UPLOAD_PATH = "/var/excel/";
    /**
     * 生成excel模板的模板名
     */
    String EXCELMODELNAME = "进销数据表.xls";

    /**
     * xls类型扩展名
     */
    String EXCELTYPEXLS = ".xls";
    /**
     * xlsx类型扩展名
     */
    String EXCELTYPEXLSX = ".xlsx";

    /**
     * 保存用户信息
     */
    String STAFFINFO = "staff";

    /**
     * 保存用户信息的集合
     */
    String STAFFINFOLIST = "stafflist";
    /**
     * 保存进销项数据集合
     */
    String DATALIST="datalist";
    /**
     * 状态
     */
    String STATUS = "status";

    /**
     * 消息
     */
    String MESSAGE = "message";

    /**
     * 图片验证码
     */
    String SRAND = "sRand";

    /**
     * 短信验证码smsCode
     */
    String SMSCODE = "smsCode";

    /**
     * 邮箱验证码
     */
    String EMAILCODE = "emailCode";

    /**
     * email
     */
    String EMAIL = "email";

    /**
     * phone
     */
    String PHONE = "phone";

    /**
     * birthday 生日
     */
    String TOSTRINGBIRTHDAY = "toStringBirthday";

    /**
     * enter 入职时间
     */
    String TOSTRINGENTER = "toStringEnter";

    /**
     * 字符串男
     */
    String STR_SEX_MAN = "男";

    /**
     * 字符串女
     */
    String STR_SEX_WOMAN = "女";

    /**
     * session失效时间
     **/
    String SESSION_INVALID_TIME = "SESSION_INVALID_TIME";


    /**
     *
     */
    String REDIS_CACHE_KEY = "key";


    /**
     *
     */
    String INPUTDATA="进项数据";

    /**
     *
     */
    String OUTPUTDATA="销项数据";


}
