package com.enttax.dao;

import com.enttax.model.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {

    /**
     * @param sId
     * @return
     */
    int deleteByPrimaryKey(String sId);

    /**
     * @param sId
     * @return
     */
    Staff selectByPrimaryKey(String sId);

    /**
     * @return
     */
    List<Staff> selectAll();

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(Staff record);

    /**
     * 添加用户
     *
     * @param record
     * @return
     */
    int insert(Staff record);

    /**
     * 添加用户和角色之间的关系
     *
     * @param sId
     * @param rId
     * @return
     */
    int insertStaffAndRoleRelation(@Param("sId") String sId, @Param("rId") String rId);

    /**
     * 一个电话号码只能代表一个合法用户
     *
     * @param sPhone
     * @return
     */
    Staff selectByPhone(@Param("sPhone") String sPhone);

    /**
     * 一个邮箱只能代表一个合法用户
     *
     * @param sEmail
     * @return
     */
    Staff selectByEmail(@Param("sEmail") String sEmail);

    /**
     * 找回密码（也就是重置密码）
     *
     * @param sId
     * @param newPassword
     * @return
     */
    int updatePassword(@Param("sId") String sId, @Param("newPassword") String newPassword, @Param("sSalt") String sSalt);
}