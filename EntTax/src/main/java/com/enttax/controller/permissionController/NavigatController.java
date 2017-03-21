package com.enttax.controller.permissionController;

import com.enttax.util.tools.ToolImageCode;
import com.enttax.util.tools.ToolSendSms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by lcyanxi on 17-3-19.
 */
@Controller
public class NavigatController {
    /**
     * 产生随机验证码
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/captcha")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        ToolImageCode.doPost(request,response);
        return "index";
    }
    @RequestMapping(value = "/sendSMS")
    public void    sendSMS(@RequestParam("phone") String phone,HttpServletRequest request){
        System.out.println(phone);
        String smsCode=  ToolSendSms.sendSMS(phone);
//        if (smsCode==null){
//            return "error";
//        }else{
            request.getSession().setAttribute("smsCode",smsCode);
//            return "successful";
//        }
    }
    @RequestMapping(value = "/validaSMS")
    public String validaSMS(@RequestParam("code") String code,HttpServletRequest request){
        System.out.println(code);
        String smsCode=(String)request.getSession().getAttribute("smsCode");
        System.out.println(smsCode);
        if (!code.equals(smsCode)){
            return "error";
        }

        return "login";
    }

    public String updataPassword(@RequestParam("code") String code,
                                  @RequestParam("smsCode") String smsCode){
        return null;

    }

}
