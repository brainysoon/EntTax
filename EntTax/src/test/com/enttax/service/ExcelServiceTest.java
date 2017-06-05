package com.enttax.service;

import com.enttax.model.Bill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brainy on 17-5-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class ExcelServiceTest {

    @Autowired
    private RedisTemplate<String, List<Bill>> template;

    @Resource(name = "redisTemplate")
    private ListOperations<String, List<Bill>> listOps;

    @Test
    public void redisTest() {

        List<Bill> bills = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            Bill bill = new Bill();
            bill.setBName("name" + i);
            bills.add(bill);
        }

        listOps.leftPush("123", bills);

        List<Bill> list = listOps.leftPop("123");

        list.size();
    }
}
