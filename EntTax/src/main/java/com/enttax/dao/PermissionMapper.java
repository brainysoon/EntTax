package com.enttax.dao;

import com.enttax.model.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(String pid);

    int insert(Permission record);

    Permission selectByPrimaryKey(String pid);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);
}