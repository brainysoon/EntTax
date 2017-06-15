package com.enttax.dao;

import com.enttax.model.Bill;
import com.enttax.vo.BillInfo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 显示月度统计数据
     * @param year
     * @return
     */
    List<BillInfo> selectMonthBill(@Param("year") String year);

    /**
     * 显示年度统计数据
     * @return
     */
    List<BillInfo> selectYearBill();
}