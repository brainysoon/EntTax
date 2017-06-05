package com.enttax.web;

/**
 * Created by brainy on 17-5-20.
 */
public interface Constant {

    /**
     * 上传Excel 数据确认成功
     */
    String UPLOAD_EXCEL_AND_CONFIRM_SUCCESSED = "确认数据导入成功";

    /**
     * 上传Excel 数据确认失败
     */
    String UPLOAD_EXCEL_AND_CONFIRM_FAILED = "确认数据导入失败";

    /**
     * 当前登录用户在  session中的  key
     */
    String CURRENT_LOGIN_STAFF_KEY = "staff";

    /**
     * 所有消息
     */
    String MODEL_KEY_ALL_MESSAGE = "allmessage";

    /**
     * 已读消息
     */
    String MODEL_KEY_READ_MESSAGE = "readmessage";

    /**
     * 未读消息
     */
    String MODEL_KEY_UNREAD_MESSAGE = "unreadmessage";

    /**
     * 已读
     */
    int MARK_READ = 1;

    /**
     * 未读
     */
    int MARK_UNREAD = -1;
}
