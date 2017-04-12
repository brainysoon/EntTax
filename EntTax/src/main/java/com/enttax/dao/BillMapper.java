package com.enttax.dao;

import com.enttax.model.Bill;
import java.util.List;

public interface BillMapper {
    int deleteByPrimaryKey(String bid);

    int insert(Bill record);

    Bill selectByPrimaryKey(String bid);

    List<Bill> selectAll();

    int updateByPrimaryKey(Bill record);
}