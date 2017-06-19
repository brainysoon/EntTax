package com.enttax.service.impl;

import com.enttax.dao.BillMapper;
import com.enttax.model.Bill;
import com.enttax.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by brainy on 17-6-19.
 */
@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public Map doLinearRegByTaxOutKey(String key) {

        Bill target = billMapper.selectByPrimaryKey(key);

        int month = target.getBMonth();

        //准备前六个月


        return null;
    }
}
