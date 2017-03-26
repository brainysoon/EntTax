package com.enttax.dao;

import com.enttax.model.Role;
import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String rid);

    int insert(Role record);

    Role selectByPrimaryKey(String rid);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
}