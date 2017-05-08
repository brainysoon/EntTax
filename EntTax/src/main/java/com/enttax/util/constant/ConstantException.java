package com.enttax.util.constant;

/**
 * Created by lcyanxi on 17-3-17.
 */
public interface ConstantException {
    //    -----------------------------------------------异常状态码配置  start-----------------------------------------------------
    /**
     * 100 接口请求成功响应码
     */
    String sucess_code = "100";
    String sucess_message = "success";
    /**
     * 101 参数错误返回码
     */
    String args_error_code = "101";
    String args_error_message = "参数错误";
    /**
     * 102 未查询到数据返回码
     */
    String no_data_code = "102";
    String no_data_message = "未查询到数据";
    /**
     * 103 异常信秘返回信息状态码
     */
    String exception_code = "103";
    String exception_message = "网络错误";
    /**
     * 104 数据已经存在返回码
     */
    String exists_code = "104";
    String exists_code_message = "数据已经存在";
    /**
     * 105 未定义的错误信息
     */
    String save_data_err_code = "105";
    String save_data_err_message = "数据保存出错";
    /**
     * 106 验证码过期
     */
    String authcode_timeout_code = "106";
    String authcode_timeout_message = "验证码过期";
    /**
     * 107 短信验证码错误
     */
    String smscode_error_code = "107";
    String smscode_error_message = "短信验证码错误";
    /**
     * 108图片验证码错误
     */
    String image_error_code = "108";
    String image_error_message = "图片验证码错误";

    /**
     */
    String phone_error_code = "109";
    String phone_error_message = "电话号码不存在！";
    /**
     * 108 验证码发送频繁
     */
    String authcode_frequency_code = "108";
    String authcode_frequency_message = "验证码发送频繁";

    /**
     * 员工不存在
     */
    String STAFF_NOT_EXIST_CODE = "109";
    String STAFF_NOT_EXIST_MESSAGE = "员工不存在";


    //    -----------------------------------------------异常状态码配置  end--------------------------------------------------------
}
