package com.enttax.data;

import com.enttax.dao.BillMapper;
import com.enttax.data.util.RandomUtil;
import com.enttax.model.Bill;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by brainy on 17-5-5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class DataGenerateTest {

    @Autowired
    private BillMapper billMapper;

    /**
     * 测试生成 22位 bId
     */
    @Test
    public void generateBIdTest() {

        String bId = RandomUtil.generateBId();

        Assert.assertTrue(bId.length() == 22);
    }

    /**
     * 月份从1980年开始算起 每个月 生成 100000 个数据  分别位销项和进项
     */
    @Test
    public void dataGenerateTest() {

        //从1980 到 现在  总共444 个月
        for (int i = 1; i <= RandomUtil.MAX_MONTH; i++) {

            for (int j = 1; j <= RandomUtil.MAX_PROJECT; j++) {

                Bill bill = new Bill();

                //进项 销项 Common
                bill.setBMonth(i);
                bill.setBName(RandomUtil.PROJECT_PRE_STR + j);
                bill.setBUpdateTime(new Date());

                //进项数据
                bill.setBId(RandomUtil.generateBId());
                bill.setBType(RandomUtil.TAX_IN);
                bill.setBPrice(RandomUtil.generateBPrice());
                bill.setBMark(1);

                billMapper.insert(bill);

                //销项数据
                bill.setBId(RandomUtil.generateBId());
                bill.setBType(RandomUtil.TAX_OUT);
                bill.setBPrice(RandomUtil.generateBPrice());
                bill.setBMark(2);

                billMapper.insert(bill);
            }
        }
    }
}
