package com.enttax.service.impl;

import com.enttax.dao.BillMapper;
import com.enttax.model.Bill;
import com.enttax.service.AIService;
import com.enttax.util.tools.ToolDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by brainy on 17-6-19.
 */
@Service
public class AIServiceImpl implements AIService {

    @Autowired
    private BillMapper billMapper;

    @Override
    public Map doLinearRegByKeyAndCount(String key, Integer count) {

        //返回的信息否放这里面
        Map map = new HashMap<>();

        Bill target = billMapper.selectByPrimaryKey(key);

        int month = target.getBMonth();

        //准备前几个月
        int[] months = ToolDates.getPreSixMonth(month, 6);

        //添加到Map
//        map.put(AIService.PRE_MONTHS, months);

        double[][] series = new double[count][2];
        //依次拿到那些值
        for (int i = 0; i < count; i++) {

            Bill bill = billMapper.selectMonthAndBName(1, target.getBName(), months[5 - i] + "");

            if (bill != null) {
                series[i][0] = 5 - i;
                series[i][1] = bill.getBPrice();
            }
        }
        map.put(SERIES, series);

        //得到预测线
        map.putAll(getLinearLine(series));

        return map;
    }

    @Override
    public Map getLinearLine(double[][] series) {

        double[][] line = new double[2][2];
        line[1][0] = 0;
        line[1][1] = series[series.length - 1][1];
        line[0][0] = 6;
        line[0][1] = series[series.length - 2][1];

        double[][] point = new double[1][2];
        point[0][0] = 6;
        point[0][1] = series[series.length - 2][1];

        Map map = new HashMap();

        map.put(LINE, line);
        map.put(POINT, point);

        return map;
    }
}
