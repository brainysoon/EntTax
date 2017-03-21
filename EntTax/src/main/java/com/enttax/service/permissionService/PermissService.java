package com.enttax.service.permissionService;

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
    boolean register(Staff staff);

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
     * 找回密码
     * @param phone
     * @return
     */
    Staff findByPassword(String phone);

    boolean selectByPhone(String phone , HttpServletRequest request);
}
