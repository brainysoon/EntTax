package com.enttax.web;

import com.enttax.service.LogService;
import com.enttax.util.constant.ConstantStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lcyanxi on 17-6-21.
 */
@Controller
@RequestMapping(value = "/log")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;



    /**
     * 跳转到系统日志页面
     * @return
     */
    @RequestMapping(value = "/systemlog",method = RequestMethod.GET)
    public String toSystemLog(Model model){
        model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
        List list=logService.showLogInfo();
        model.addAttribute(ConstantStr.LOGMESSAGE,list);
        System.out.println(list);
        return "/systemlog";
    }

}
