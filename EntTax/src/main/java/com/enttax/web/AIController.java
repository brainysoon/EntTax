package com.enttax.web;

import com.enttax.model.Bill;
import com.enttax.model.Staff;
import com.enttax.service.BillService;
import com.enttax.util.constant.ConstantStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by brainy on 17-6-19.
 */
@Controller
@RequestMapping("/ai")
public class AIController extends BaseController {

    private static final SimpleDateFormat SDF_YEAR = new SimpleDateFormat("YYYY");
    private static final SimpleDateFormat SDF_MONTH = new SimpleDateFormat("MM");

    @Autowired
    private BillService billService;

    @RequestMapping(value = "/linear", method = RequestMethod.GET)
    public String linearRegression(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        //拿出当前月份有的
        Date date = new Date();
        List<Bill> bills = billService.getBillByYearAndMonth(SDF_YEAR.format(date), SDF_MONTH.format(date));
        model.addAttribute(Constant.MODEL_KEY_BILLS, bills);

        return "ai/linear";
    }

    @RequestMapping(value = "/linearreg", method = RequestMethod.GET)
    @ResponseBody
    public Map doLinearReg(@RequestParam(value = "key") String key) {

        //拿到所需要的东西
    }
}
