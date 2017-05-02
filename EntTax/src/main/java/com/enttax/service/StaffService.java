package com.enttax.service;

import com.enttax.model.Staff;
import com.enttax.vo.Profile;
import org.apache.shiro.session.Session;

/**
 * Created by brainy on 17-4-25.
 */
public interface StaffService {

    /**
     * 更新个人信息
     *
     * @param profile
     * @return
     */
    int updateStaffInfo(Profile profile, Session session);

    /**
     * @param phone
     * @return
     */
    Staff selectByPhone(String phone);

    /**
     * @param email
     * @return
     */
    Staff selectByEamil(String email);

    /**
     * 重置密码
     *
     * @param sId
     * @param newPassword
     * @return
     */
    int updatePassword(String sId, String newPassword);

    /**
     * 更新用户信息（用于更新phone和email）
     *
     * @param staff
     * @return
     */
    int updateStaff(Staff staff);

    /**
     * @param realPath
     * @param session
     * @return
     */
    int updateAvatar(String realPath, Session session);

    /**
     * @param sName
     * @return
     */
    Staff selectByIdentify(String sName);

    /**
     * @param sId
     * @return
     */
    Staff selectByPrimaryKey(String sId);
}