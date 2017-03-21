package com.enttax.controller.permissionController;

import com.enttax.model.Staff;
import com.enttax.service.permissionService.PermissService;
import com.enttax.util.constant.ConstantException;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;


/**
 *  权限模块  包括登录、注册
 * Created by lcyanxi on 17-3-13.
 */
@Controller
@RequestMapping(value = "/user")
public class PermissionController {
    private PermissService permissService;
    @Autowired
    public void setPermissService(PermissService permissService) {
        this.permissService = permissService;
    }

    /**
     *  登录功能
     * @param sname
     * @param spassword
     * @return
     */
   @RequestMapping(value = "/login")
    public String login(@RequestParam(value = "sname") String sname,
                        @RequestParam(value = "spassword") String spassword,
                        @RequestParam(value = "kcode") String kcode,
                        HttpServletRequest request,
                        Model model) {
       if (sname==null||spassword==null){
           model.addAttribute("state", ConstantException.args_error_code);
           model.addAttribute("message",ConstantException.args_error_message);
           return "index";
       }
       if (!kcode.equals(request.getSession().getAttribute("sRand"))){
           model.addAttribute("state",ConstantException.authcode_error_code);
           model.addAttribute("message",ConstantException.authcode_error_message);
           return "index";
       }
        Staff staff=  permissService.login(sname,Encodes.encodeBase64(spassword));
       System.out.println(staff);
       if (staff==null){
           model.addAttribute("state",ConstantException.no_data_code);
           model.addAttribute("message",ConstantException.no_data_message);
           return "index";
       }

       model.addAttribute("state",ConstantException.sucess_code);
       model.addAttribute("message",ConstantException.sucess_message);
       model.addAttribute("staff",staff);
       model.addAttribute("sid",staff.getSid());
       return "login";
    }


    /**
     * 注册功能  用户名和密码为必须字段0
     * @param staff
     * @return
     */
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public String register(@ModelAttribute Staff staff , Model model){
        String sid= ToolDates.getDateNum();
        staff.setSid(sid);
        staff.setSenter(new Date());
        staff.setSpassword(Encodes.encodeBase64(staff.getSpassword()));
        if (permissService.register(staff)){
            model.addAttribute("state", ConstantStr.str_one);
            model.addAttribute("sid",sid);
            return "successful";
        }else {
            model.addAttribute("state",ConstantStr.str_zero);
            return "error";
        }
    }


    /**
     * 更新个人信息
     * @param staff
     * @return
     */
    @RequestMapping(value = "/updateStaffInfo",method = RequestMethod.POST)
    public String updateStaffInfo(@ModelAttribute Staff staff){
        if(staff.getSid()==null){
            return "staffInfo";
        }
        staff.setSpassword(Encodes.encodeBase64(staff.getSpassword()));
        permissService.updateStaffInfo(staff);
        return "staffInfo";
    }



    /**
     * 头像上传
     * @param request
     * @param x
     * @param y
     * @param h
     * @param w
     * @param imageFile
     * @return
     */
    @RequestMapping(value = "/uploadHeadImage",method = RequestMethod.POST)
    public String uploadHeadImage(
            HttpServletRequest request,
            @RequestParam(value = "x") String x,
            @RequestParam(value = "y") String y,
            @RequestParam(value = "h") String h,
            @RequestParam(value = "w") String w,
            @RequestParam(value = "imgFile") MultipartFile imageFile,
            Model model) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        try {
           String savator= FileUploadUtil.uploadHeadImage(realPath,x,y,h,w,imageFile);
           if (savator==null){
               return "index";
           }

            Staff staff=(Staff) model.asMap().get("staff");
            staff.setSavator(savator);
            permissService.updateStaffInfo(staff);
        }catch (IOException e){

        }
        return "image";
    }



    public  void  findByPassword(@RequestParam(value="code") String code,
                                 @RequestParam(value = "password") String password){


    }

    /**
     * 通过电话号码找回密码
     * @param request
     * @param phone
     */
    public String selectByPhone(
            HttpServletRequest request,
            @RequestParam(value = "phone") String phone){
        if (permissService.selectByPhone(phone,request)){
            return "successful";
        }
        return "error";

    }





}
