package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lcyanxi on 17-3-19.
 */
@Controller
public class NavigatController extends BaseController {

    @Autowired
    private StaffService staffService;

    /**
     * @return 由于是分析系统  所以首页面直接返回到  登录页面 暂时没有主页面
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String toIndex(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        return "index";
    }

    /**
     * @return
     */
    @RequestMapping(value = "/unauthorized")
    public String toUnauthorized() {

        return "unauthorized";
    }

    /**
     * 显示staff列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/show_staffinfo", method = RequestMethod.GET)
    public String show_staffinfo(Model model) {

        List staffInfoList=staffService.selectAllStaffInfo();

        System.out.println(staffInfoList);
        model.addAttribute(ConstantStr.STAFFINFOLIST,staffInfoList);
        model.addAttribute(ConstantStr.STAFFINFO, session.getAttribute(ConstantStr.STAFFINFO));

        return "staffs/managestaff";
    }

    /**
     * 显示进销项数据列表
     * @return
     */
    @RequestMapping(value = "/show_date",method = RequestMethod.GET)
    public String show_date(Model model){
        model.addAttribute(ConstantStr.STAFFINFO, session.getAttribute(ConstantStr.STAFFINFO));
        return "bill/managedata";
    }
}
