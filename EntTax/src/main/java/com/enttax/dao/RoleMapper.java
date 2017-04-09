package com.enttax.dao;

import com.enttax.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Role record);

    Role selectByPrimaryKey(String rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    /**
     * 插入permission 和role 的关系
     * @param pid
     * @param sid
     * @return
     */
    int insertPerm_Role(@Param("pid") String pid,@Param("rid") String rid);
}