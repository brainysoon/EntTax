package com.enttax.service.permissionService;

import com.enttax.model.Staff;

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
}
