package com.enttax.dao;

import com.enttax.model.Perms;
import com.enttax.util.tools.ToolRandoms;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by brainy on 17-4-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class PermsMapperTest {

    @Autowired
    private PermsMapper permsMapper;

    /**
     * 测试查询所有的权限  资源权限
     */
    @Test
    public void selectAll() {

        //查询
        List<Perms> permses = permsMapper.selectAll();

        //断言
        Assert.assertNotNull(permses);
    }

    /**
     * 测试插入
     */
    @Test
    public void insertTest() {

        //准备模拟数据
        Perms perms = new Perms();
        perms.setPId(ToolRandoms.randomId4());
        int num = ToolRandoms.random.nextInt() % 100;
        perms.setPName("perms_" + num);
        perms.setPLabel("权限" + num);
        perms.setPType(1);
        perms.setPMark(1);

        //插入
        int resultCode = permsMapper.insert(perms);

        //断言
        Assert.assertEquals(resultCode, 1);
    }
}
