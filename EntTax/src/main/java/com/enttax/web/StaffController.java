package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantException;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.CookieUtil;
import com.enttax.util.tools.FileUploadUtil;
import com.enttax.util.tools.ToolDates;
import com.enttax.vo.Profile;
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
 * Created by brainy on 17-4-25.
 */
@Controller
@RequestMapping(value = "/staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

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
        if (staffService.updateStaffInfo(profile, session) > 0) {
            model.addAttribute(ConstantStr.MESSAGE, "更改成功！！");
        } else {
            model.addAttribute(ConstantStr.MESSAGE, "更改失败！！");
        }
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);
        model.addAttribute(ConstantStr.TOSTRINGBIRTHDAY,   //转换日期格式
                ToolDates.formatDate(staff.getSBirthday()));
        return "staff/editprofile";
    }

    /**
     * 更新电话号码
     *
     * @param sphone
     * @param model
     * @return
     */
    @RequestMapping(value = "/updatephone", method = RequestMethod.POST)
    public String updatePhone(@RequestParam(value = "sphone") String sphone,
                              Model model) {

        if (sphone == null || sphone == "") {
            model.addAttribute(ConstantStr.MESSAGE, "电话号码不能为空");
            return "staff/phoneresetpass";
        }
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        staff.setSPhone(sphone);
        if (staffService.updateStaff(staff) > 0) {
            session.setAttribute(ConstantStr.STAFFINFO, staff);
            model.addAttribute(ConstantStr.STAFFINFO, staff);
            return "staff/security";
        }
        model.addAttribute(ConstantStr.MESSAGE, "电话号码绑定失败");

        return "staff/phoneresetpass";
    }

    /**
     * 更新email
     *
     * @param semail
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateemail", method = RequestMethod.POST)
    public String updateEmail(@RequestParam(value = "semail") String semail,
                              Model model) {
        if (semail == null || semail == "") {
            model.addAttribute(ConstantStr.MESSAGE, "邮箱不能为空");
            return "staff/resetemail";
        }
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        staff.setSEmail(semail);
        if (staffService.updateStaff(staff) > 0) {
            session.setAttribute(ConstantStr.STAFFINFO, staff);
            model.addAttribute(ConstantStr.STAFFINFO, staff);
            return "staff/security";

        }
        model.addAttribute(ConstantStr.MESSAGE, "邮箱绑定失败");
        return "staff/resetemail";

    }

    /**
     * 更新头像
     * @param imageFile
     * @return
     */
    @RequestMapping(value = "/updateavatar", method = RequestMethod.POST)
    @ResponseBody
    public Map updateAvator(@RequestParam(value = "uploadfile") MultipartFile imageFile) {
        Map map = new HashMap();
        try {
            //把头像存在文件夹里  数据库存头像的地址
            String savatorPath = FileUploadUtil.uploadHeadImage(ConstantStr.IMAGEUPLOADPATH, imageFile);
            System.out.println("savatorPath:" + savatorPath);
            if (savatorPath != null) {    //判断文件是否存在文件夹里
                //判断数据库是否更新了头像路劲
                if (staffService.updateAvatar(savatorPath, session) > 0) {
                    map.put(ConstantStr.STATUS, true);
                    return map;
                } else {

                    map.put(ConstantStr.STATUS, false);
                }
            }
        } catch (IOException e) {
            map.put(ConstantStr.STATUS, false);
            map.put(ConstantStr.MESSAGE, "文件写入失败！！");
        }
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

            resultView = "staff/phoneresetpass";
        } else if (emailCode != null) {

            resultView = "staff/emailresetpass";
        }

        if (spassword == null || spassword == "") {
            model.addAttribute(ConstantStr.MESSAGE, "密码不能为空！！");
            return resultView;

        } else {
            if (code.equals(smsCode) || code.equals(emailCode)) {

                String sid = (String) session.getAttribute(ConstantStr.SID);

                if (staffService.updatePassword(sid, spassword) > 0) {
                    model.addAttribute(ConstantStr.MESSAGE, "密码更新成功！！");
                    return "staff/login";
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
                        @RequestParam(value = "rememberme", defaultValue = "false", required = false) Boolean rememberme,
                        Model model) {

        //判断参数是否为空
        if (sname == null || sname.equals("") || spassword == null || spassword.equals("")) {
            model.addAttribute(ConstantStr.STATUS, ConstantException.args_error_code);
            model.addAttribute(ConstantStr.MESSAGE, ConstantException.args_error_message);
            return "staff/login";
        }

        //判断验证码是否正确
        if (!kcode.toLowerCase().equals(request.getSession().getAttribute(ConstantStr.SRAND))) {
            model.addAttribute(ConstantStr.STATUS, ConstantException.image_error_code);
            model.addAttribute(ConstantStr.MESSAGE, ConstantException.image_error_message);
            return "staff/login";
        }

        //构建登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(sname, spassword);
        token.setRememberMe(rememberme);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {

                //设置 登录信息
                String principal = (String) subject.getPrincipal();
                session.setAttribute(ConstantStr.STAFFINFO, staffService.selectByIdentify(principal));

                //添加  Cookie
                CookieUtil.getInstance().addCookie(response, "sname", sname, CookieUtil.COOKIE_MAX_AGE);

                return "redirect:/";
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
        return "staff/login";
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout() {

        //退出登录
        subject.logout();

        return "redirect:/staff/login";
    }

    /**
     * @return 返回登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {

        return "staff/login";
    }

    /**
     * 导航到 选择找回密码方式界面
     *
     * @return
     */
    @RequestMapping(value = "/chooseway", method = RequestMethod.GET)
    public String toChooseWay() {

        return "staff/chooseway";
    }

    /**
     * 导航到 通过邮箱重置密码
     *
     * @return
     */
    @RequestMapping(value = "/emailresetpass", method = RequestMethod.GET)
    public String toMailResetPass() {

        return "staff/emailresetpass";
    }

    /**
     * 导航到 通过手机重置密码
     *
     * @return
     */
    @RequestMapping(value = "/phoneresetpass", method = RequestMethod.GET)
    public String toPhoneResetPass() {

        return "staff/phoneresetpass";
    }

    /**
     * 显示个人信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String toShowProfile(Model model) {

        //拿到 Staff
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);

        model.addAttribute(ConstantStr.STAFFINFO, staff);
        //转换日期格式
        model.addAttribute(ConstantStr.TOSTRINGBIRTHDAY,
                ToolDates.formatDate(staff.getSBirthday()));
        //转换日期格式
        model.addAttribute(ConstantStr.TOSTRINGENTER,
                ToolDates.formatDate(staff.getSEnter()));

        return "staff/profile";
    }

    /**
     * 带着个人信息跳到profile_edit页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public String toEditProfile(Model model) {
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);
        //转换日期格式
        model.addAttribute(ConstantStr.TOSTRINGBIRTHDAY,
                ToolDates.formatDate(staff.getSBirthday()));
        return "staff/editprofile";
    }

    /**
     * 个人安全信息页面
     *
     * @return
     */
    @RequestMapping(value = "/security", method = RequestMethod.GET)
    public String toSecurity(Model model) {
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);
        return "staff/security";
    }

    @RequestMapping(value = "/resetphone", method = RequestMethod.GET)
    public String toResetPhone() {

        return "staff/resetphone";
    }

    @RequestMapping(value = "/resetemail", method = RequestMethod.GET)
    public String toResetMail() {

        return "staff/resetemail";
    }

    /**
     * 管理员通过sid更改员工角色
     * @param sId
     * @param rName
     * @return
     */
    @RequestMapping(value = "/updatestaff",method = RequestMethod.POST)
    @ResponseBody
    public Map updateStaffForRole(@RequestParam(value = "sId") String sId,
                                  @RequestParam(value = "rName") String rName){
        Map map=new HashMap();
        System.out.println(sId+rName);
        if (sId==null||sId==""){
            map.put(ConstantStr.MESSAGE,"对不起，您输入的参数有误！");
            return map;
        }

        if (staffService.updateStaffForRole(sId,rName)>0){
            map.put(ConstantStr.MESSAGE,"恭喜您，操作成功！");
        }else {
            map.put(ConstantStr.MESSAGE,"对不起,操作失败！");
        }
        return map;

    }

    /**
     * 管理员删除员工
     * @param sid
     * @return
     */
    @RequestMapping(value = "/deletestaff",method = RequestMethod.GET)
    @ResponseBody
    public Map deleteStaffBySid(@RequestParam(value = "sid") String sid){
        Map map=new HashMap();

        if (sid==null||sid==""){
            map.put(ConstantStr.MESSAGE,"对不起，您输入的参数有误！");
            return map;
        }

        if (staffService.deleteStaffBySid(sid)>0){
            map.put(ConstantStr.MESSAGE,"恭喜您，操作成功！");
        }else {
            map.put(ConstantStr.MESSAGE,"对不起,操作失败！");
        }
      
        return map;

    }

    /**
     * 管理员添加员工
     * @param sPhone
     * @param role
     * @return
     */
    @RequestMapping(value = "/add_staff",method = RequestMethod.POST)
    @ResponseBody
    public Map addStaff(@RequestParam(value = "sPhone") String sPhone,
                        @RequestParam(value = "role") String role){
        Map map=new HashMap();
        if (sPhone==null||sPhone==""){
            map.put(ConstantStr.MESSAGE,"添加失败,电话号码不能为空");
            return map;
        }

        if (staffService.addStaff(sPhone,role)>0){
            map.put(ConstantStr.MESSAGE,"添加成功！");
        }else {
            map.put(ConstantStr.MESSAGE,"添加失败,该电话号码已注册！");
        }
        return map;
    }

}

