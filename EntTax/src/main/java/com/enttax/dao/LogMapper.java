package com.enttax.dao;

import com.enttax.model.Log;

import java.util.List;

public interface LogMapper {

    /**
     * @param lId
     * @return
     */
    int deleteByPrimaryKey(String lId);

    /**
     * @param record
     * @return
     */
    int insert(Log record);

    /**
     * @param lId
     * @return
     */
    Log selectByPrimaryKey(String lId);

    /**
     * @return
     */
    List<Log> selectAll();

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(Log record);
}