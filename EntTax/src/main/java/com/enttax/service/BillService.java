package com.enttax.service;

import com.enttax.model.Bill;

import java.util.List;
import java.util.Map;

/**
 * Created by lcyanxi on 17-4-7.
 */
public interface BillService {

    /**
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Bill> select(int pageNum, int pageSize);

    /**
     * @param map
     * @return
     */
    boolean insertExcelData(Map<Object, Object> map);

    /**
     * @return
     */
    List<Bill> selectAll();

    /**
     * @param bMark
     * @return
     */
    List<Bill> selectByBMark(Integer bMark);

    /**
     * @param bName
     * @return
     */
    List<Bill> selectByBName(String bName);

    /**
     * @param bills
     * @return
     */
    int insertAll(List<Bill> bills);

    /**
     * 通过id删除bill数据
     * @param bId
     * @return
     */
    int deleteBillById(String bId);

    /**
     * 更新bill数据
     * @param bill
     * @return
     */
    int updateBill(Bill bill);


    /**
     * 显示月度统计
     * @return
     */
    Map showMonthBill(String year);

    /**
     * 显示年度统计
     * @return
     */
    Map showYearBill();

    /**
     * 显示所有类型项目名称
     * @return
     */
    Map showCategoryName();

    /**
     * 显示分类统计数据
     * @param year
     * @param inputbName
     * @param outputbName
     * @return
     */
    Map showCategoryBill(String year,String inputbName,String outputbName);


    /**
     * 显示比率统计数据
     * @param year
     * @return
     */
    Map showRateCountBill(String year);
}
