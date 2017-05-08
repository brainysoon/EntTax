package com.enttax.dao;

import com.enttax.model.Role;
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
 * Created by brainy on 17-4-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class RoleMapperTest {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 测试查询所有的 角色
     */
    @Test
    public void selectAllTest() {

        //查询所有的角色
        List<Role> roles = roleMapper.selectAll();

        //断言 结果不为空
        Assert.assertNotNull(roles);
    }

    /**
     * 测试插入一个角色
     */
    @Test
    public void insertTest() {

        //生成测试数据
        Role role = new Role();
        role.setRId(ToolRandoms.randomId4());
        int num = ToolRandoms.random.nextInt()%100;
        role.setRName("ROLE_" + num);
        role.setRLabel("角色" + num);
        role.setRUpdateTime(new Date());
        role.setRMark(1);

        //插入
        int resultCode = roleMapper.insert(role);

        //断言    有且紧影响一行数据
        Assert.assertEquals(resultCode, 1);
    }
}
