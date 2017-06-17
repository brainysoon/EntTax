package com.enttax.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * Created by lcyanxi on 17-6-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class BillServiceTest {
    @Autowired
    private BillService billService;
    @Test
    public void showYearBillTest(){

       Map map= billService.showYearBill();
       System.out.println(map);

    }

    @Test
    public void showCategoryNamesTest(){
        System.out.println(billService.showCategoryName());
    }

    @Test
    public void showCategoryBillTest(){
        System.out.println(billService.showCategoryBill("2016","鸡肉","铁皮"));
    }
}
