package com.enttax.service;

import com.enttax.model.Staff;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lcyanxi on 17-3-13.
 */
public interface PermissService {
    /**
     *  注册功能
     * @param staff
     * @return
     */
    boolean register(Staff staff,String rid);

    /**
     * 登录功能
     * @param sname
     * @param password
     * @return
     */
    Staff login(String sname,String password);

    /**
     *  更新个人信息
     * @param staff
     * @return
     */

    int updateStaffInfo(Staff staff);

    /**
     * 通过用户名查找用户是否存在
     * @param phone
     * @param request
     * @return
     */
    boolean selectByPhone(String phone , HttpServletRequest request);

    /**
     * 重置密码
     * @param sid
     * @param newPassword
     * @return
     */
    boolean updateToPassword(String sid,String newPassword);

    Staff selectByUserName(String username);

}
