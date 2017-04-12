package com.enttax.dao;

import com.enttax.model.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(String sid);
    Staff selectByPrimaryKey(String sid);

    List<Staff> selectAll();

    int updateByPrimaryKey(Staff record);

    /**
     * 添加用户
     * @param record
     * @return
     */
    int insert(Staff record);

    /**
     * 添加用户和角色之间的关系
     * @param sid
     * @param rid
     * @return
     */
    int insertStaffAndRoleRelation(@Param("sid") String sid,@Param("rid") String rid);

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



    List<Staff> selectByUserName(@Param("sname") String sname);

    /**
     * 找回密码（也就是重置密码）
     * @param sid
     * @param newpassword
     * @return
     */
    int updateToPassword(@Param("sid") String sid,@Param("newpassword") String newpassword);
}