package com.enttax.controller.permissionController;

import com.enttax.model.Staff;
import com.enttax.service.permissionService.PermissService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.Encodes;
import com.enttax.util.tools.FileUploadUtil;
import com.enttax.util.tools.ToolRandoms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
@SessionAttributes("sid")
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
                        Model model) {
       if (sname==null||spassword==null){
           model.addAttribute("state",ConstantStr.str_zero);
           return "login";
       }
        Staff staff=  permissService.login(sname,Encodes.encodeBase64(spassword));
       System.out.println(staff);
       if (staff==null){
           model.addAttribute("state",ConstantStr.str_zero);
           return "login";
       }

       model.addAttribute("state",ConstantStr.str_one);
       model.addAttribute("staff",staff);
       model.addAttribute("sid",staff.getSid());
        return "redirect:/user/test";
    }


    /**
     * 注册功能  用户名和密码为必须字段
     * @param staff
     * @return
     */
    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
    public String register(@ModelAttribute Staff staff , Model model){
        String sid=ToolRandoms.randomCode8();
        staff.setSid(sid);
        staff.setSenter(new Date());
        staff.setSpassword(Encodes.encodeBase64(staff.getSpassword()));
        if (permissService.register(staff)){
            model.addAttribute("state", ConstantStr.str_one);
            model.addAttribute("sid",sid);
            Map modelMap = model.asMap();
            for (Object modelKey : modelMap.keySet()) {
                Object modelValue = modelMap.get(modelKey);
                System.out.println(modelKey + " -- " + modelValue);
            }
        }else {
            model.addAttribute("state",ConstantStr.str_zero);
        }
        return "successful";
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
            @RequestParam(value = "imgFile") MultipartFile imageFile
    ) {
        String realPath = request.getSession().getServletContext().getRealPath("/");
        try {
           String savator= FileUploadUtil.uploadHeadImage(realPath,x,y,h,w,imageFile);

           if (savator==null){
               return "index";
           }

        }catch (IOException e){

        }


        return "image";
    }
    @RequestMapping(value = "/test")
    public  String test(@ModelAttribute Model model){
        Map modelMap = model.asMap();
        for (Object modelKey : modelMap.keySet()) {
            Object modelValue = modelMap.get(modelKey);
            System.out.println(modelKey + " -- " + modelValue);
        }
        return "index";
    }



}
