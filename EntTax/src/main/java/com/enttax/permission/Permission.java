package com.enttax.permission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lcyanxi on 17-3-12.
 */
@Controller
public class Permission {
    @RequestMapping(value = "/")
    public void login(){
        System.out.println("hello  world");
    }
}
