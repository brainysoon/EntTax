package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.PermissService;
import com.enttax.util.constant.ConstantException;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.Encodes;
import com.enttax.util.tools.FileUploadUtil;
import com.enttax.util.tools.ToolDates;
import com.enttax.vo.Profile;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 权限模块  包括登录、注册
 * Created by lcyanxi on 17-3-13.
 */
@Controller
@RequestMapping(value = "/user")
public class PermissionController extends BaseController {
    private static final Logger logger = Logger.getLogger(PermissionController.class);
    private PermissService permissService;

    @Autowired
    public void setPermissService(PermissService permissService) {
        this.permissService = permissService;
    }


    /**
     * 注册功能  用户名和密码为必须字段0
     *
     * @param staff
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam("rid") String rid,
                           @ModelAttribute Staff staff, Model model) {
        boolean result = permissService.register(staff, rid);
        if (result) {
            model.addAttribute(ConstantStr.STATUS, ConstantStr.str_one);
//            model.addAttribute("sid",sid);
            return "successful";
        } else {
            model.addAttribute(ConstantStr.STATUS, ConstantStr.str_zero);
            return "error";
        }
    }

    /**
     * 显示个人信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/showprofile", method = RequestMethod.GET)
    public String showProfile(Model model) {
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        if (staff == null) {
            return "login";
        }
        model.addAttribute(ConstantStr.STAFFINFO, staff);
        //转换日期格式
        model.addAttribute(ConstantStr.TOSTRINGBIRTHDAY,
                ToolDates.formatDate(staff.getSbirthday()));
        //转换日期格式
        model.addAttribute(ConstantStr.TOSTRINGENTER,
                ToolDates.formatDate(staff.getSenter()) );

        return "profile";
    }

    /**
     * 带着个人信息跳到profile_edit页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "profile_edit", method = RequestMethod.GET)
    public String editProfile(Model model) {
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);
        //转换日期格式
        model.addAttribute(ConstantStr.TOSTRINGBIRTHDAY,
                ToolDates.formatDate(staff.getSbirthday()));
        return "profile_edit";
    }

    /**
     * 更新个人信息
     *
     * @param profile
     * @return
     */
    @RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
    public String updateStaffInfo(@ModelAttribute Profile profile, Model model) {
        //先拿到存在session里的staff
        System.out.println(profile);
        if (permissService.updateStaffInfo(profile, session)>0){
            model.addAttribute(ConstantStr.MESSAGE,"更改成功！！");
        }else {
            model.addAttribute(ConstantStr.MESSAGE,"更改失败！！");
        }
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO,staff);
        model.addAttribute(ConstantStr.TOSTRINGBIRTHDAY,   //转换日期格式
                ToolDates.formatDate(staff.getSbirthday()));
        return "profile_edit";
    }

    /**
     * 更新电话号码
     * @param sphone
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatephone",method = RequestMethod.POST)
    public String updatePhone(@RequestParam(value = "sphone") String sphone,
                              Model model){

        if(sphone==null||sphone==""){
            model.addAttribute(ConstantStr.MESSAGE,"电话号码不能为空");
            return "phonereset";
        }
            Staff staff=(Staff) session.getAttribute(ConstantStr.STAFFINFO);
            staff.setSphone(sphone);
            if (permissService.updateStaff(staff)>0){
                session.setAttribute(ConstantStr.STAFFINFO,staff);
                model.addAttribute(ConstantStr.STAFFINFO,staff);
                return "personal_security";
            }
        model.addAttribute(ConstantStr.MESSAGE,"电话号码绑定失败");

        return "phonereset";
    }


    /**
     * 更新email
     * @param semail
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateemail",method = RequestMethod.POST)
    public String updateEmail(@RequestParam(value = "semail") String semail,
                              Model model){
        if (semail==null||semail==""){
            model.addAttribute(ConstantStr.MESSAGE,"邮箱不能为空");
            return "mailedit";
        }
        Staff staff=(Staff) session.getAttribute(ConstantStr.STAFFINFO);
        staff.setSemail(semail);
        if (permissService.updateStaff(staff)>0){
            session.setAttribute(ConstantStr.STAFFINFO,staff);
            model.addAttribute(ConstantStr.STAFFINFO,staff);
            return "personal_security";

        }
        model.addAttribute(ConstantStr.MESSAGE,"邮箱绑定失败");
        return "mailedit";

    }

    /**
     * 更新头像
     *
     * @param imageFile
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateimage", method = RequestMethod.POST)
    public String uploadHeadImage(@RequestParam(value = "uploadfile") MultipartFile imageFile,
                                  Model model) {
//        String realPath = session.getServletContext().getRealPath("/");
        try {
            //把头像存在文件夹里  数据库存头像的地址
//            String savatorPath = FileUploadUtil.uploadHeadImage(realPath, imageFile);
            String savatorPath = FileUploadUtil.uploadHeadImage(ConstantStr.IMAGEUPLOADPATH, imageFile);
            System.out.println("savatorPath:"+savatorPath);
            if (savatorPath != null) {    //判断文件是否存在文件夹里
                //判断数据库是否更新了头像路劲
                if (permissService.updateHeadImage(savatorPath, session) > 0) {
                    model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
                    model.addAttribute(ConstantStr.MESSAGE, "头像更改成功！！");
                    return "profile_edit";
                }
            }
        } catch (IOException e) {
          logger.info("uploadHeadImage 出现"+e+"异常");
        }
        model.addAttribute(ConstantStr.MESSAGE, "头像更改失败！！");
        model.addAttribute(ConstantStr.STAFFINFO,session.getAttribute(ConstantStr.STAFFINFO));
        return "profile_edit";
    }

    @RequestMapping(value = "/updateavator",method = RequestMethod.POST)
    @ResponseBody
    public Map updateAvator(@RequestParam(value = "uploadfile") MultipartFile imageFile){
        Map map =new HashMap();
        try {
            //把头像存在文件夹里  数据库存头像的地址
            String savatorPath = FileUploadUtil.uploadHeadImage(ConstantStr.IMAGEUPLOADPATH, imageFile);
            System.out.println("savatorPath:"+savatorPath);
            if (savatorPath != null) {    //判断文件是否存在文件夹里
                //判断数据库是否更新了头像路劲
                if (permissService.updateHeadImage(savatorPath, session) > 0) {
                    map.put(ConstantStr.STATUS,ConstantStr.str_one);
                    return map;
                }
            }
        } catch (IOException e) {
            logger.info("uploadHeadImage 出现"+e+"异常");
        }
        map.put(ConstantStr.MESSAGE,"头像更改失败！！");
        return map;
    }


    /**
     * 重置密码
     *
     * @param spassword
     */
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    public String updateToPassword(@RequestParam(value = "spassword") String spassword,
                                   @RequestParam(value = "code") String code, Model model) {

        String smsCode = (String) session.getAttribute(ConstantStr.SMSCODE);
        String emailCode = (String) session.getAttribute(ConstantStr.EMAILCODE);

        String resultView = "login";

        if (smsCode != null) {

            resultView = "phonereset";
        } else if (emailCode != null) {

            resultView = "mailreset";
        }

        if (spassword == null || spassword == "") {
            model.addAttribute(ConstantStr.MESSAGE, "密码不能为空！！");
            return resultView;

        } else {
            if (code.equals(smsCode) || code.equals(emailCode)) {

                String sid = (String) session.getAttribute(ConstantStr.SID);

                if (permissService.updateToPassword(sid, Encodes.encodeBase64(spassword))) {
                    model.addAttribute(ConstantStr.MESSAGE, "密码更新成功！！");
                    return "login";
                } else {
                    model.addAttribute(ConstantStr.MESSAGE, "密码更新失败!！");
                }
            } else {
                model.addAttribute(ConstantStr.MESSAGE, "验证码不正确！！");
            }


        }
        return resultView;
    }

    /**
     * 处理登响应
     *
     * @param sname
     * @param spassword
     * @param kcode
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam("sname") String sname,
            @RequestParam("spassword") String spassword,
            @RequestParam("kcode") String kcode,
            Model model) {

        Map<String, String> map = new HashMap<String, String>();

        //判断参数是否为空
        if (sname == null || sname.equals("") || spassword == null || spassword.equals("")) {
            model.addAttribute(ConstantStr.STATUS, ConstantException.args_error_code);
            model.addAttribute(ConstantStr.MESSAGE, ConstantException.args_error_message);
            return "login";
        }
        //判断验证码是否正确
        if (!kcode.toLowerCase().equals(request.getSession().getAttribute(ConstantStr.SRAND))) {
            model.addAttribute(ConstantStr.STATUS, ConstantException.image_error_code);
            model.addAttribute(ConstantStr.MESSAGE, ConstantException.image_error_message);
            return "login";
        }

        UsernamePasswordToken token = new UsernamePasswordToken(sname, spassword);
        token.setRememberMe(true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
                model.addAttribute(ConstantStr.STATUS, ConstantException.sucess_code);
                model.addAttribute(ConstantStr.STAFFINFO, staff);
                return "index";
            }
        } catch (IncorrectCredentialsException e) {
            model.addAttribute(ConstantStr.MESSAGE, "登录密码错误");
        } catch (ExcessiveAttemptsException e) {
            model.addAttribute(ConstantStr.MESSAGE, "登录失败次数过多");
        } catch (LockedAccountException e) {
            model.addAttribute(ConstantStr.MESSAGE, "帐号已被锁定");
        } catch (DisabledAccountException e) {
            model.addAttribute(ConstantStr.MESSAGE, "帐号已被禁用");
        } catch (ExpiredCredentialsException e) {
            model.addAttribute(ConstantStr.MESSAGE, "帐号已过期");
        } catch (UnknownAccountException e) {
            model.addAttribute(ConstantStr.MESSAGE, "帐号不存在");
        } catch (UnauthorizedException e) {
            model.addAttribute(ConstantStr.MESSAGE, "您没有得到相应的授权！");
        }

        model.addAttribute(ConstantStr.STATUS, ConstantStr.str_zero);
        return "login";
    }
}
