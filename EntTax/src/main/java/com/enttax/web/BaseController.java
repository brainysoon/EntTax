package com.enttax.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lcyanxi on 17-3-23.
 */
@Controller
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected Subject subject;
    protected Session session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

//         废弃 容器自带的 Session 实现
//        this.session = request.getSession();

        //得到当前用户的 Subject
        subject = SecurityUtils.getSubject();
        session = subject.getSession();
    }
}
