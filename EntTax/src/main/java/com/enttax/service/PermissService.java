package com.enttax.service;

import com.enttax.model.Staff;
import com.enttax.vo.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
     * @param profile
     * @return
     */

    int updateStaffInfo(Profile profile,HttpSession session);

    /**
     * 通过用户名查找用户是否存在
     * @param phone
     * @param request
     * @return
     */
    boolean selectByPhone(String phone , HttpServletRequest request);

    /**
     * 通过email查找用户是否存在
     * @param email
     * @return
     */
    boolean selectByEamil(String email, HttpSession session);

    /**
     * 重置密码
     * @param sid
     * @param newPassword
     * @return
     */
    boolean updateToPassword(String sid,String newPassword);

    /**
     * 更新用户信息（用于更新phone和email）
     * @param staff
     * @return
     */
    int updateStaff(Staff staff);

    int updateHeadImage(String realPath,HttpSession session);


    Staff selectByUserName(String username);

}
