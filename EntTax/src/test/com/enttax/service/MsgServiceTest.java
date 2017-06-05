package com.enttax.service;

import com.enttax.dao.StaffMapper;
import com.enttax.model.Staff;
import com.enttax.util.tools.ToolRandoms;
import com.enttax.vo.MsgInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
@FixMethodOrder(MethodSorters.DEFAULT)
public class MsgServiceTest {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private MsgService msgService;

    private Staff toStaff;
    private Staff fromStaff;

    @Before
    public void randomSelectTwoStaff() {

        //查出所有的员工
        List<Staff> staffs = staffMapper.selectAll();

        //随机挑选两个员工
        toStaff = staffs.get((Math.abs(ToolRandoms.random.nextInt()) % staffs.size()));
        fromStaff = staffs.get((Math.abs(ToolRandoms.random.nextInt()) % staffs.size()));
    }

    @Test
    public void sendMsgTest() {

        //构建发送消息
        String toSId = toStaff.getSId();
        String fromSId = fromStaff.getSId();
        String content = "lala 消息" + ToolRandoms.random.nextInt();

        //发送
        int result = msgService.sendMsg(toSId, content, fromSId);

        //断言
        Assert.assertEquals(result, 1);
    }

    @Test
    public void getMsgByToSIdTest() {

        //查询对应用户的消息
        List<MsgInfo> msgs = msgService.getMsgByToSId(toStaff.getSId());

        //断言
        Assert.assertNotNull(msgs);
    }

    @Test
    public void markReadByMIdsTest() {

        //准备数据
        String[] mIds = getmIds();

        //标记
        int result = msgService.markReadByMIds(mIds);

        //断言
        Assert.assertEquals(result, mIds.length);
    }

    @Test
    public void deleteByMIdsTest() {

        //准备数据
        String[] mIds = getmIds();

        //删除
        int result = msgService.deleteByMIds(mIds);

        //断言
        Assert.assertEquals(result, mIds.length);
    }

    private String[] getmIds() {
        //查询对应用户的消息
        List<MsgInfo> msgs = msgService.getMsgByToSId(toStaff.getSId());

        //准备数据
        String[] mIds = new String[msgs.size()];
        for (int i = 0; i < msgs.size(); i++) {

            mIds[i] = msgs.get(i).getMId();
        }

        return mIds;
    }
}
