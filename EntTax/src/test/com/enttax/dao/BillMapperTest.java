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
    public void showMonthBillTest() {
        List<BillInfo> list = billMapper.selectMonthBill("2017");

        System.out.println(list);
    }

    @Test
    public void showAllbNameTest() {
        System.out.println(billMapper.selectAllbName("进项数据"));
    }

    @Test
    public void showCategoryBillTest() {
        List<BillInfo> list = billMapper.selectCategoryBill("2016", "鸡肉", "进项数据");
        System.out.println(list);
    }

    @Test
    public void showRateCountBillTest(){
        List<BillInfo> inputlist = billMapper.selectRateCountBill("2016", ConstantStr.INPUTDATA);
        List<BillInfo> outputlist = billMapper.selectRateCountBill("2016", ConstantStr.OUTPUTDATA);
        Map map=new HashMap();
        map.put("input",dataToList(inputlist));
        map.put("output",dataToList(outputlist));
        System.out.println(map);


    }

    private List dataToList(List<BillInfo> billInfos){
        List  list2=new ArrayList();
        for (BillInfo billInfo :billInfos) {
            List  list1=new ArrayList();
            list1.add("\'"+billInfo.getbName()+"\'");
            list1.add(billInfo.getTotalPrice());
            list2.add(list1);
        }
        return list2;
    }

}
