package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.util.constant.ConstantStr;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lcyanxi on 17-3-19.
 */
@Controller
public class NavigatController extends BaseController {

    /**
     * 导航到重置密码页面
     *
     * @return
     */
    @RequestMapping(value = "/updatepassword")
    public String updatepasswordPage() {
        return "updatepassword";
    }

    /**
     * 导航到主页面
     *
     * @return
     */
    @RequestMapping(value = "/home")
    public String mainPage(Model model) {
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(staff);
        return "home";
    }

    /**
     * 导航到找回密码页面
     *
     * @return
     */
    @RequestMapping(value = "/findpassword")
    public String findpasswordPage() {
        return "findpassword";
    }

    /**
     * 安全退出
     *
     * @return
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut() {
        session.removeAttribute(ConstantStr.STAFFINFO);
        session.invalidate();
        return "redirect:/index";
    }

    /**
     * 导航到 选择找回密码方式界面
     *
     * @return
     */
    @RequestMapping(value = "/findways", method = RequestMethod.GET)
    public String toFindWays() {

        return "findways";
    }

    /**
     * 导航到 通过邮箱重置密码
     *
     * @return
     */
    @RequestMapping(value = "/mailreset", method = RequestMethod.GET)
    public String toMailReset() {

        return "mailreset";
    }

    /**
     * 导航到 通过手机重置密码
     *
     * @return
     */
    @RequestMapping(value = "/phonereset", method = RequestMethod.GET)
    public String toPhoneReset() {

        return "phonereset";
    }

    /**
     * @return 返回登录页面
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String toLogin() {

        return "login";
    }

    /**
     * 个人安全信息页面
     * @return
     */
    @RequestMapping(value = "/user/personalsecurity" ,method = RequestMethod.GET)
    public String toPersoanlSecurity(Model model){
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);
        return "personal_security";
    }
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){

        return "index";
    }
}
