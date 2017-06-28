package com.enttax.service.impl;

import com.enttax.dao.BillMapper;
import com.enttax.model.Bill;
import com.enttax.service.AIService;
import com.enttax.util.tools.ToolDates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;

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

        try {


            //返回的信息否放这里面
            Map map = new HashMap<>();

            Bill target = billMapper.selectByPrimaryKey(key);

            int month = target.getBMonth();

            //准备前几个月
            int[][] months = ToolDates.getPreSixMonth(month, 6);

            //添加到Map
//        map.put(AIService.PRE_MONTHS, months);

            double[][] series = new double[count][2];
            //依次拿到那些值
            for (int i = 0; i < count; i++) {

                Bill bill = billMapper.selectMonthAndBName(1, target.getBName(),
                        months[5 - i][0] + "", months[5 - i][1] + "");

                if (bill != null) {
                    series[i][0] = 5 - i;
                    series[i][1] = bill.getBPrice();
                }
            }
            map.put(SERIES, series);

            //得到预测线
            map.putAll(getLinearLine(series));

            return map;

        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public Map getLinearLine(double[][] series) {

        //先准备两个矩阵
        Matrix X = DenseMatrix.Factory.ones(series.length, 2);

        //复制
        for (int i = 0; i < series.length; i++) {

            X.setAsDouble(series[i][0], i, 1);
        }

        Matrix Y = DenseMatrix.Factory.zeros(series.length, 1);

        //赋值
        for (int i = 0; i < series.length; i++) {

            Y.setAsDouble(series[i][1], i, 0);
        }

        //转置矩阵
        Matrix transX = X.transpose();

        //矩阵相乘
        Matrix mtimesX = transX.mtimes(X);

        //逆矩阵
        Matrix invX = mtimesX.inv();

        //再运算
        Matrix theta = invX.mtimes(transX).mtimes(Y);

        //预测数据
        double[][] point = new double[1][2];
        point[0][0] = 6;
        point[0][1] = theta.getAsDouble(1, 0) * 6 + theta.getAsDouble(0, 0);

        double[][] line = new double[2][2];
        line[1][0] = 0;
        line[1][1] = theta.getAsDouble(1, 0) * 0 + theta.getAsDouble(0, 0);
        line[0][0] = 6;
        line[0][1] = theta.getAsDouble(1, 0) * 6 + theta.getAsDouble(0, 0);
        Map map = new HashMap();

        map.put(LINE, line);
        map.put(POINT, point);

        return map;
    }
}
