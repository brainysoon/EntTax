package com.enttax.controller.permissionController;

import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolImageCode;
import com.enttax.util.tools.ToolSendSms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    @RequestMapping(value = "/findpassword")
    String findPassword(HttpServletRequest request,HttpServletResponse response){
        return "findpassword";
    }
    @RequestMapping(value = "/validacode")
    @ResponseBody
    public Map<String,String> validacode(HttpServletRequest request,
                          @RequestParam("code") String code,
                          @RequestParam("numcode") String numcode){
        System.out.println(code+numcode);
        Map<String,String> map=new HashMap<String, String>();
        String sRand = (String) request.getSession().getAttribute("sRand");
        String smsCode= (String) request.getSession().getAttribute("smsCode");
        if (code.equals(sRand) && numcode.equals(smsCode)){
            map.put("status", ConstantStr.str_one);
        }else {
            map.put("status",ConstantStr.str_zero);
        }

        return map;

    }

}
