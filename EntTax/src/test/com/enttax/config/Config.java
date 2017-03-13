package com.enttax.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/**
 * Created by lcyanxi on 17-3-12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/springmvc.xml", "classpath:spring/spring-mybatis.xml"})
public class Config {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void configTest() throws  Exception{
        this.mockMvc.perform(get("/")
        );

    }

    @Test
    public void registerTest() throws  Exception{
        this.mockMvc.perform(post("/user/register")
         .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("sid","1212121")
                .param("sname","奥巴马")
                .param("spassword","lc121718")
                .param("semail","845892601@qq.com")
                .param("sphone","13125723136")
                .param("ssex","1")
                .param("sbirthday","1993-12-17")
                .param("senter" ,"'"+new Date()+"'")
                .param("smark","1")
                .param("saddress","重庆市黔江区")
                .param("savator","c://user/image")
        )
                .andDo(print())
                .andExpect(view().name("successful"))
                .andReturn();
    }
}
