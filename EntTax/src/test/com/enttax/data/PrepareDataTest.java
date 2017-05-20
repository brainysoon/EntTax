package com.enttax.data;

import com.enttax.ml.PrepareData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by brainy on 17-5-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class PrepareDataTest {

    @Autowired
    private PrepareData prepareData;

    /**
     *
     */
    @Test
    public void writeDataToDistTest() {

        //将进项和销项数据 写入文件
        prepareData.writeDataToDisk(PrepareData.DEFAULT_TAX_IN_FILE_NAME, 1);
        prepareData.writeDataToDisk(PrepareData.DEFAULT_TAX_OUT_FILE_NAME, 2);

        //写入销项和进项的差值 以及月份
        prepareData.writeDataToDiskAboutDiff(PrepareData.DEFAULT_TAX_DIFF_FILE_NAME);

        //写入销项数据 和 月份
        int result = prepareData.writeDataToDiskTaxOutAndMonth(PrepareData.DEFAULT_TAX_OUT_AND_MONTH_FILE_NAME);
        System.out.println(result);
    }
}
