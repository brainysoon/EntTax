package com.enttax.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

/**
 * Created by brainy on 17-4-28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    /**
     * 测试 hash 加密 密码
     */
    @Test
    public void encodePasswordTest() {

        //模拟数据  密码  SId
        String password = "sxc19940115";
        String preSalt = "20171546";

        //加密
        Map<String, String> map = securityService.encodePassword(password, preSalt);

        //获取数据
        String encodePassword = map.get(SecurityService.ENCODE_RESULT_KEY_PASSWORD);
        String subSalt = map.get(SecurityService.ENCODE_RESULT_KEY_SALT);

        //断言
        Assert.assertNotNull(encodePassword);
        Assert.assertNotNull(subSalt);

        //打印结果
        System.out.println("encodePassword : " + encodePassword);
        System.out.println("subSalt : " + subSalt);
    }
}
