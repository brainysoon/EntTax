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

import java.io.File;

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
                .param("sname","习近平")
                .param("spassword","84589lljkc7788")
                .param("semail","845892601@qq.com")
                .param("sphone","13125723136")
                .param("ssex","1")
                .param("sbirthday","1993-12-17")
                .param("smark","1")
                .param("saddress","重庆市黔江区")
                .param("savator","c://user/image")
        )
                .andDo(print())
                .andExpect(view().name("successful"))
                .andReturn();
    }
    @Test
    public void updateStaffInfoTest() throws  Exception{
        this.mockMvc.perform(post("/user/updateStaffInfo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("sid","12345678")
                .param("sname","丽娜")
                .param("spassword","lc02041217")
                .param("semail","845892601@qq.com")
                .param("sphone","13125723136")
                .param("ssex","1")
                .param("sbirthday","1993-12-17")
                .param("smark","1")
                .param("saddress","重庆市黔江区")
                .param("savator","c://user/image")
        )
                .andDo(print())
                .andExpect(view().name("staffInfo"))
                .andReturn();
    }

    @Test
    public void loginTest() throws  Exception{
        this.mockMvc.perform(post("/user/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("sname","奥朗普")
                .param("spassword","84589lljkc")
        )
                .andDo(print())
                .andExpect(view().name("index"))
                .andReturn();
    }

    @Test
    public void imageUploadTest() throws  Exception{
        this.mockMvc.perform(post("/user/uploadHeadImage")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .param("x","50")
                .param("y","50")
                .param("h","50")
                .param("w","50")

        )
                .andDo(print())
                .andExpect(view().name("index"))
                .andReturn();
    }
}
