package com.enttax.service;

import org.apache.shiro.session.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by lcyanxi on 17-6-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/applicationContext.xml"})
public class StaffServiceTest {
    @Autowired
    private StaffService staffService;

    @Test
    public void updateStaffTest(){
//       System.out.println(staffService.updateStaffForRole("20177064","管理员", ));
    }
}
