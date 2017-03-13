package com.enttax.controller.permissionController;

import com.enttax.model.Staff;
import com.enttax.service.permissionService.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *  权限模块  包括登录、注册
 * Created by lcyanxi on 17-3-13.
 */
@Controller
@RequestMapping(value = "/user")
public class PermissionController {
    private RegisterService registerService;
    @Autowired
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public String login(){
        return null;
    }


    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public String register(@ModelAttribute Staff staff){
        System.out.println("aaaaaaaaa");
        System.out.println(staff.getSname());
        registerService.register(staff);
        return "successful";
    }
}
