package com.enttax.dao;

import com.enttax.model.Staff;
import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(String sid);

    int insert(Staff record);

    Staff selectByPrimaryKey(String sid);

    List<Staff> selectAll();

    int updateByPrimaryKey(Staff record);
}