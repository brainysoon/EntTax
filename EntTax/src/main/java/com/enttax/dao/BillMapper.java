package com.enttax.dao;

import com.enttax.model.Bill;

import java.util.List;

public interface BillMapper {

    /**
     * @param bId
     * @return
     */
    int deleteByPrimaryKey(String bId);

    /**
     * @param record
     * @return
     */
    int insert(Bill record);

    /**
     * @param bId
     * @return
     */
    Bill selectByPrimaryKey(String bId);

    /**
     * @return
     */
    List<Bill> selectAll();

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(Bill record);

    /**
     * @param bMark
     * @return
     */
    List<Bill> selectByBMark(int bMark);

    /**
     * @param bName
     * @return
     */
    List<Bill> selectByBName(String bName);

    /**
     * @param list
     * @return
     */
    int insertAll(List<Bill> list);
}