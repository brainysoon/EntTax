package com.enttax.web;

import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantException;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.SendEmail;
import com.enttax.util.tools.ToolImageCode;
import com.enttax.util.tools.ToolSendSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcyanxi on 17-4-18.
 */
@Controller
public class VaildateController extends BaseController {

    @Autowired
    private StaffService staffService;


    /**
     * 产生随机图片验证码
     *
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "/sendcaptcha")
    public void captcha() throws IOException, ServletException {
        ToolImageCode.doPost(request, response);
    }

    /**
     * 查找电话号码是否存在,如果存在则发送短信验证码
     *
     * @param sphone
     */
    @RequestMapping(value = "/sendsmscode", method = RequestMethod.GET)
    @ResponseBody
    public Map sendByPhone(@RequestParam(value = "sphone") String sphone) {

        Map map = new HashMap();
        if (sphone.equals("") || sphone == null) {

            map.put(ConstantStr.STATUS, false);
            map.put(ConstantStr.MESSAGE, ConstantException.args_error_message);
            return map;
        }
        boolean isExistPhone = staffService.selectByPhone(sphone) == null;

        if (isExistPhone) {
            String smsCode = ToolSendSms.sendSMS(sphone);//发送短信
            if (smsCode == null) {
                map.put(ConstantStr.STATUS, false);
                map.put(ConstantStr.MESSAGE, ConstantException.exception_message);
            } else {
                session.setAttribute(ConstantStr.SMSCODE, smsCode);
                map.put(ConstantStr.STATUS, true);
            }

        } else {
            map.put(ConstantStr.STATUS, false);
            map.put(ConstantStr.MESSAGE, ConstantException.no_data_message);
        }

        return map;
    }

    /**
     * 检查 email 是否存在,存在则发送邮箱验证码
     *
     * @param semail
     * @return
     */
    @RequestMapping(value = "/sendemailcode", method = RequestMethod.GET)
    @ResponseBody
    public Map sendByEmail(@RequestParam(value = "semail") String semail) {
        Map map = new HashMap();
        if (semail == null || semail.equals("")) {
            map.put(ConstantStr.STATUS, false);
            map.put(ConstantStr.MESSAGE, ConstantException.args_error_message);
            return map;
        }
        if (staffService.selectByEamil(semail) == null) { //判断邮箱是否存在于数据库
            String eamilCode = SendEmail.sendEmail(semail);     //发送邮箱验证码
            if (eamilCode != null) {    //判断是否拿到邮箱验证码
                session.setAttribute(ConstantStr.EMAILCODE, eamilCode);
                map.put(ConstantStr.STATUS, true);
            } else {
                map.put(ConstantStr.STATUS, false);
                map.put(ConstantStr.MESSAGE, ConstantException.exception_message);
            }

        } else {
            map.put(ConstantStr.STATUS, false);
            map.put(ConstantStr.MESSAGE, ConstantException.no_data_message);
        }

        return map;
    }

    /**
     * 判断图片验证码是否正确
     *
     * @param kcode 图片验证码
     * @throws IOException
     */
    @RequestMapping(value = "/checkcaptcha", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkCaptcha(@RequestParam("kcode") String kcode) {

        //判断验证码是否正确，并将结果输出 这里去除了大小写铭感
        return kcode.toLowerCase()
                .equals(request.getSession().getAttribute(ConstantStr.SRAND));
    }

    /**
     * 校验短信验证码是否正确
     *
     * @param smscode 短息验证码
     * @return
     */
    @RequestMapping(value = "/checksmscode", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkSmsCode(@RequestParam(value = "code") String smscode) {

        String sessionSmsCode = (String) session.getAttribute(ConstantStr.SMSCODE);
        return smscode.equals(sessionSmsCode);
    }

    /**
     * 校验邮箱验证码是否正确
     *
     * @param emailcode 邮箱验证码
     * @return
     */
    @RequestMapping(value = "/checkemailcode", method = RequestMethod.GET)
    @ResponseBody
    public Boolean checkEmailCode(@RequestParam(value = "code") String emailcode) {

        String sessionEmailCode = (String) session.getAttribute(ConstantStr.EMAILCODE);
        return emailcode.equals(sessionEmailCode);
    }
}
