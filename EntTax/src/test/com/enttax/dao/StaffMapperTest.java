package com.enttax.dao;

import com.enttax.model.Role;
import com.enttax.model.Staff;
import com.enttax.service.SecurityService;
import com.enttax.service.impl.CommonLog;
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
import java.util.Map;

/**
 * Created by brainy on 17-4-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class StaffMapperTest {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SecurityService securityService;

    /**
     * 测试查询出所有的  staff
     */
    @Test
    public void selectAllTest() {

        //查询所有的员工
        List<Staff> staffs = staffMapper.selectAll();

        //断言 不为空
        Assert.assertNotNull(staffs);
    }

    /**
     * 测试插入一个员工
     */
    @Test
    public void insertTest() {

        //准备测试用用户信息
        String sId = ToolRandoms.randomCode8();
        String sName = "测试";
        String prePassword = "dbroom1411";
        Map<String, String> map = securityService.encodePassword(prePassword, sId);
        String sPassword = map.get(SecurityService.ENCODE_RESULT_KEY_PASSWORD);
        String sSalt = map.get(SecurityService.ENCODE_RESULT_KEY_SALT);
        String sEmail = ToolRandoms.randomEmail();
        String sPhone = ToolRandoms.randomPhoneNum();

        //测试员工
        Staff staff = new Staff();
        staff.setSId(sId);
        staff.setSName(sName);
        staff.setSPassword(sPassword);
        staff.setSSalt(sSalt);
        staff.setSEmail(sEmail);
        staff.setSPhone(sPhone);
        staff.setSSex(true);
        staff.setSBirthday(new Date());
        staff.setSEnter(new Date());
        staff.setSMark(1);
        staff.setSAddress("我的家在东北");
        staff.setSAvatar("http://localhost/avatar.png");

        //开始插入
        int resultCode = staffMapper.insert(staff);

        //断言 影响一行记录
        Assert.assertEquals(resultCode, 1);
    }

    @Test
    public void insertStaffTest(){
        //添加员工
        Staff staff = new Staff();
        String sId = "20173333";

        //拿到加密后的密码和摘要
        Map map = securityService.encodePassword("dbroom1411", sId);
        String sPassword = (String) map.get(SecurityService.ENCODE_RESULT_KEY_PASSWORD);
        String Ssalt = (String) map.get(SecurityService.ENCODE_RESULT_KEY_SALT);


            staff.setSId(sId);
            staff.setSName("李华");
            staff.setSPassword(sPassword);
            staff.setSSalt(Ssalt);
            staff.setSEnter(new Date());
            staff.setSSex(true);
            staff.setSMark(0);
            staff.setSPhone("13166956162");
            staff.setSAvatar("../../img/avatar.jpg");

            staffMapper.insert(staff);


            //添加角色
            Role role1 = new Role();
            String rId = ToolRandoms.randomId4();
            role1.setRId(rId);
            role1.setRName("决策员");
            role1.setRMark(1);
            role1.setRUpdateTime(new Date());

            roleMapper.insert(role1);


            //添加员工与角之间的关系
            staffMapper.insertStaffAndRoleRelation(sId, rId);


        }

    /**
     * 测试更新员工信息
     */
    @Test
    public void updateByPrimaryKey() {

        //查询所有的员工
        List<Staff> staffs = staffMapper.selectAll();

        //取查到的第一个员工
        Staff staff = staffs.get(0);

        //修改信息
        staff.setSEmail(ToolRandoms.randomEmail());

        //测试更新
        int resultCode = staffMapper.updateByPrimaryKey(staff);

        //断言
        Assert.assertEquals(resultCode, 1);
    }
}
