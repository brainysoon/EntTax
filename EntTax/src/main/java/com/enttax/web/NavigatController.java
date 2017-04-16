package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.util.constant.ConstantException;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.ToolImageCode;
import com.enttax.util.tools.ToolSendSms;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Created by lcyanxi on 17-3-19.
 */
@Controller
public class NavigatController extends BaseController {
    /**
     * 产生随机图片验证码
     *
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/captcha")
    public void captcha() throws IOException, ServletException {
        ToolImageCode.doPost(request, response);
    }

    /**
     * 判断图片验证码是否正确
     *
     * @param kcode 图片验证码
     * @throws IOException
     */
    @RequestMapping(value = "/checkcaptcha", method = RequestMethod.POST)
    public void checkCaptcha(@RequestParam("kcode") String kcode) throws IOException {

        //判断验证码是否正确，并将结果输出 这里去除了大小写铭感
        response.getWriter().print(kcode.toLowerCase()
                .equals(request.getSession().getAttribute(ConstantStr.SRAND)));
    }

    /**
     * @param numcode
     * @return
     */
    @RequestMapping(value = "/validacode")
    @ResponseBody

    public Map<String, String> validacode(@RequestParam("numcode") String numcode) {
//        @RequestParam("code") String code,
//        System.out.println(code+numcode);
        Map<String, String> map = new HashMap<String, String>();
//        String sRand = (String) request.getSession().getAttribute(ConstantStr.SRAND);
        String smsCode = (String) request.getSession().getAttribute(ConstantStr.SMSCODE);
//        code.equals(sRand) &&
        if (numcode.equals(smsCode)) {
            map.put(ConstantStr.STATUS, ConstantStr.str_one);
        } else {
            map.put(ConstantStr.STATUS, ConstantStr.str_zero);
            map.put(ConstantStr.MESSAGE, ConstantException.smscode_error_message);
        }

        return map;
    }

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
     * 校验短信验证码是否正确
     *
     * @param smscode 短息验证码
     * @return
     */
    @RequestMapping(value = "/checksmscode", method = RequestMethod.POST)
    public boolean checkSmsCode(@RequestParam(value = "smscode") String smscode) {
        String sessionSmsCode = (String) session.getAttribute(ConstantStr.SMSCODE);
        if (smscode.equals(sessionSmsCode)) {
            return true;
        }
        return false;
    }

    /**
     * 校验邮箱验证码是否正确
     *
     * @param emailcode 邮箱验证码
     * @return
     */
    @RequestMapping(value = "checkemailcode",method = RequestMethod.POST)
    public boolean checkEmailCode(@RequestParam(value = "emailcode") String emailcode) {
        String sessionEmailCode = (String) session.getAttribute(ConstantStr.EMAILCODE);
        if (emailcode.equals(sessionEmailCode)) {
            return true;
        }
        return false;
    }


}
