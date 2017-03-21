package com.enttax.dao;

import com.enttax.model.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(String sid);

    int insert(Staff record);

    Staff selectByPrimaryKey(String sid);

    List<Staff> selectAll();

    int updateByPrimaryKey(Staff record);

    /**
     * 用户名，密码登录
     * @param sname
     * @param spassword
     * @return
     */
    Staff selectByNameAndPassword(@Param("sname") String sname,@Param("spassword") String  spassword);

    /**
     * 邮箱，密码登录
     * @param email
     * @param spassword
     * @return
     */
    Staff selectByEmailAndPassword(@Param("semail") String email,@Param("spassword") String spassword);

    /**
     * 电话号码，密码登录
     * @param sphone
     * @param spassword
     * @return
     */
    Staff selectByPhoneAndPassword(@Param("sphone") String sphone,@Param("spassword") String spassword);

    /**
     * 一个电话号码只能代表一个合法用户
     * @param sphone
     * @return
     */
    List<Staff> selectByPhone(@Param("sphone") String sphone);

    /**
     * 一个邮箱只能代表一个合法用户
     * @param semail
     * @return
     */
    List<Staff> selectByEmail(@Param("semail") String semail);
}