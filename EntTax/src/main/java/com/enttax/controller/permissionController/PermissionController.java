package com.enttax.controller.permissionController;

import com.enttax.model.Staff;
import com.enttax.service.permissionService.RegisterService;
import com.enttax.util.tools.Encodes;
import com.enttax.util.tools.ToolRandoms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

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


    /**
     * 注册功能  用户名和密码为必须字段
     * @param staff
     * @return
     */
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public String register(@ModelAttribute Staff staff){
        staff.setSid(ToolRandoms.randomCode8());
        staff.setSenter(new Date());
        staff.setSpassword(Encodes.encodeBase64(staff.getSpassword()));
        registerService.register(staff);
        return "successful";
    }
}
