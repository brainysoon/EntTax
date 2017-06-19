package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.util.constant.ConstantStr;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by brainy on 17-6-19.
 */
@Controller
@RequestMapping("/ai")
public class AIController extends BaseController {

    @RequestMapping(value = "/linear", method = RequestMethod.GET)
    public String linearRegression(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        return "ai/linear";
    }
}
