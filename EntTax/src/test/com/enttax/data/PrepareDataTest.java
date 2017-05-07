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

    private static final String TAX_IN_DIR = "/var/ml/tax_in.txt";
    private static final String TAX_OUT_DIR = "/var/ml/tax_out.txt";

    /**
     *
     */
    @Test
    public void writeDataToDistTest() {

        prepareData.writeDataToDisk(TAX_IN_DIR, 1);
        prepareData.writeDataToDisk(TAX_OUT_DIR, 2);
    }
}
