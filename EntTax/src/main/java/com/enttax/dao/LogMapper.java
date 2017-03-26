package com.enttax.dao;

import com.enttax.model.Log;
import java.util.List;

public interface LogMapper {
    int deleteByPrimaryKey(String lid);

    int insert(Log record);

    Log selectByPrimaryKey(String lid);

    List<Log> selectAll();

    int updateByPrimaryKey(Log record);
}