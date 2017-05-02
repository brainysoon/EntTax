package com.enttax.dao;

import com.enttax.model.Perms;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermsMapper {

    /**
     * @param pId
     * @return
     */
    int deleteByPrimaryKey(String pId);

    /**
     * @param record
     * @return
     */
    int insert(Perms record);

    /**
     * @param pId
     * @return
     */
    Perms selectByPrimaryKey(String pId);

    /**
     * @return
     */
    List<Perms> selectAll();

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(Perms record);

    /**
     * @param rId
     * @return
     */
    List<Perms> selectByRId(@Param("rId") String rId);
}