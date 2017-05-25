package com.enttax.dao;

import com.enttax.model.Msg;
import com.enttax.model.Staff;
import com.enttax.util.tools.ToolRandoms;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class MsgMapperTest {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private MsgMapper msgMapper;

    @Test
    public void insertTest() {

        List<Staff> staffs = staffMapper.selectAll();

        int toStaff = ToolRandoms.random.nextInt();
        int fromStaff = ToolRandoms.random.nextInt();

        toStaff = Math.abs(toStaff);
        fromStaff = Math.abs(fromStaff);

        toStaff = toStaff % staffs.size();
        fromStaff = fromStaff % staffs.size();

        String mId = ToolRandoms.randomId20();

        Msg msg = new Msg();
        msg.setMId(mId);
        msg.setToSId(staffs.get(toStaff).getSId());
        msg.setFromSId(staffs.get(fromStaff).getSId());
        msg.setMRead(-1);
        msg.setMContent("Msg" + ToolRandoms.random.nextInt());
        msg.setMDate(new Date());
        msg.setMMark(1);

        int result = msgMapper.insert(msg);

        Assert.assertEquals(result, 1);
    }
}
