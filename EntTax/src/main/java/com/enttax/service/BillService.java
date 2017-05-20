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
}
