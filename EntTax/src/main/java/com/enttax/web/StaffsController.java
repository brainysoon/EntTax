package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by brainy on 17-5-20.
 */
@Controller
@RequestMapping(value = "/staffs")
public class StaffsController extends BaseController {

    @Autowired
    private StaffService staffService;

    /**
     * 显示staff列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/managestaff", method = RequestMethod.GET)
    public String show_staffinfo(Model model) {

        List staffInfoList = staffService.selectAllStaffInfo();

        System.out.println(staffInfoList);
        model.addAttribute(ConstantStr.STAFFINFOLIST, staffInfoList);
        model.addAttribute(ConstantStr.STAFFINFO, session.getAttribute(ConstantStr.STAFFINFO));

        return "staffs/managestaff";
    }

    /**
     * 管理员通过sid更改员工角色
     *
     * @param sId
     * @param rName
     * @return
     */
    @RequestMapping(value = "/updatestaff", method = RequestMethod.POST)
    @ResponseBody
    public Map updateStaffForRole(@RequestParam(value = "sId") String sId,
                                  @RequestParam(value = "rName") String rName) {

        Map map = new HashMap();
        if (sId == null || sId == "") {
            map.put(ConstantStr.MESSAGE, "对不起，您输入的参数有误！");
            return map;
        }

        if (staffService.updateStaffForRole(sId, rName,session) > 0) {
            map.put(ConstantStr.MESSAGE, "恭喜您，操作成功！");
        } else {
            map.put(ConstantStr.MESSAGE, "对不起,操作失败！");
        }
        return map;

    }

    /**
     * 管理员删除员工
     *
     * @param sid
     * @return
     */
    @RequestMapping(value = "/deletestaff", method = RequestMethod.GET)
    @ResponseBody
    public Map deleteStaffBySid(@RequestParam(value = "sid") String sid) {
        Map map = new HashMap();

        if (sid == null || sid == "") {
            map.put(ConstantStr.MESSAGE, "对不起，您输入的参数有误！");
            return map;
        }

        if (staffService.deleteStaffBySid(sid,session) > 0) {
            map.put(ConstantStr.MESSAGE, "恭喜您，操作成功！");
        } else {
            map.put(ConstantStr.MESSAGE, "对不起,操作失败！");
        }

        return map;

    }

    /**
     * 管理员添加员工
     *
     * @param sPhone
     * @param role
     * @return
     */
    @RequestMapping(value = "/addstaff", method = RequestMethod.POST)
    @ResponseBody
    public Map addStaff(@RequestParam(value = "sPhone") String sPhone,
                        @RequestParam(value = "role") String role) {
        Map map = new HashMap();
        if (sPhone == null || sPhone == "") {
            map.put(ConstantStr.MESSAGE, "添加失败,电话号码不能为空");
            return map;
        }

        if (staffService.addStaff(sPhone, role,session) > 0) {
            map.put(ConstantStr.STATUS,ConstantStr.str_one);
            map.put(ConstantStr.MESSAGE, "添加成功！");
        } else {
            map.put(ConstantStr.MESSAGE, "添加失败,该电话号码已注册！");
        }
        return map;
    }
}
