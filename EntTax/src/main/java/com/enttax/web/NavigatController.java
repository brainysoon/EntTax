package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.util.constant.ConstantException;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolImageCode;
import com.enttax.util.tools.ToolSendSms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcyanxi on 17-3-19.
 */
@Controller
public class NavigatController extends BaseController {
    /**
     * 产生随机图片验证码
     * @return
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/captcha")
    public String index() throws IOException,ServletException{
        ToolImageCode.doPost(request,response);
        return "index";
    }

//    @RequestMapping(value = "/sendSMS")
//    @ResponseBody
//    public  Map<String,String>  sendSMS(@RequestParam("phone") String phone){
//        Map<String,String> map=new HashMap<String, String>();
//        String smsCode=  ToolSendSms.sendSMS(phone);
//        if (smsCode==null){
//            map.put(ConstantStr.STATUS, ConstantException.phone_error_code);
//            map.put(ConstantStr.MESSAGE,ConstantException.phone_error_message);
//        }else{
//            session.setAttribute(ConstantStr.SMSCODE,smsCode);
//            map.put(ConstantStr.STATUS,ConstantException.sucess_code);
//        }
//        return map;
//    }

    /**
     *
     * @param numcode
     * @return
     */
    @RequestMapping(value = "/validacode")
    @ResponseBody
    public Map<String,String> validacode(@RequestParam("numcode") String numcode){
//        @RequestParam("code") String code,
//        System.out.println(code+numcode);
        Map<String,String> map=new HashMap<String, String>();
//        String sRand = (String) request.getSession().getAttribute(ConstantStr.SRAND);
        String smsCode= (String) request.getSession().getAttribute(ConstantStr.SMSCODE);
//        code.equals(sRand) &&
        if ( numcode.equals(smsCode)){
            map.put(ConstantStr.STATUS, ConstantStr.str_one);
        }else {
            map.put(ConstantStr.STATUS,ConstantStr.str_zero);
            map.put(ConstantStr.MESSAGE,ConstantException.smscode_error_message);
        }

        return map;

    }

    /**
     * 导航到重置密码页面
     * @return
     */
    @RequestMapping(value = "/updatepassword")
    public String updatepasswordPage(){
        return "updatepassword";
    }

    /**
     * 导航到主页面
     * @return
     */
    @RequestMapping(value = "/home")
    public String mainPage(Model model){
       Staff staff=(Staff) session.getAttribute(ConstantStr.STAFFINFO);
       model.addAttribute(staff);
        return "home";
    }

    /**
     * 导航到找回密码页面
     * @return
     */
    @RequestMapping(value = "/findpassword")
    public String findpasswordPage(){
        return "findpassword";
    }

    /**
     * 安全退出
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(){
        session.removeAttribute(ConstantStr.STAFFINFO);
        session.invalidate();
        return "redirect:/index";
    }

    @RequestMapping(value = "/")
    public void indexTest(){
        System.out.println("hello world");
    }

}
