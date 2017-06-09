package com.enttax.dao;

import com.enttax.model.Bill;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolRandoms;
import com.enttax.vo.BillInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.*;

/**
 * Created by brainy on 17-4-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class BillMapperTest {

    @Autowired
    private BillMapper billMapper;

    /**
     * 测试查询数据
     */
    @Test
    public void selectAllTest() {

        //查询  Bill
        List<Bill> bills = billMapper.selectAll();

        //断言不为空
        Assert.assertNotNull(bills);
    }

    /**
     * 测试插入一条 数据
     */
    @Test
    public void insertTest() {

        //生成数据
        Bill bill = new Bill();
        bill.setBId(ToolRandoms.randomBillId());
        bill.setBType("进项数据");
        bill.setBName("猪肉");
        bill.setBMonth(1);
        bill.setBPrice(ToolRandoms.random.nextDouble());
        bill.setBUpdateTime(new Date());
        bill.setBMark(1);

        //插入数据
        int resultCode = billMapper.insert(bill);

        //断言 影响一行记录
        Assert.assertEquals(resultCode, 1);
    }

    @Test
    public void showMonthBillTest(){
       List<BillInfo> list= billMapper.selectMonthBill("2017");

        List inputList = new ArrayList();
        List outputList = new ArrayList();
        Map<Integer ,Double> inputMap=new HashMap();
        Map<Integer,Double>  outputMap=new HashMap();

        int inputindex=1;
        int outputindex=1;

        for (BillInfo billInfo : list) {
            if (billInfo.getbType().equals(ConstantStr.INPUTDATA)) {
                inputMap.put(billInfo.getbMonth(),billInfo.getTotalPrice());

            } else {
                outputMap.put(billInfo.getbMonth(),billInfo.getTotalPrice());
            }

        }


            for (Integer key : inputMap.keySet()) {
                if (key==inputindex){
                    inputList.add(inputMap.get(key));
                    inputindex++;
                }else {
                    inputList.add(0);
                    inputindex++;
                }
            }
            for (;inputindex<=12; inputindex++){
                inputList.add(0);
            }


            for (Integer key : outputMap.keySet()) {
                if (key==outputindex){
                    outputList.add(outputMap.get(key));
                    outputindex++;
                }else {
                    outputList.add(0);
                    outputindex++;
                }
            }
        for (;outputindex<=12; outputindex++){
            outputList.add(0);
        }

        System.out.println(inputList);
        System.out.println(outputList);
    }
}
