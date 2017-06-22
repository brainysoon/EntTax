package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.MsgService;
import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.vo.MsgInfo;
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

    @Autowired
    private MsgService msgService;

    /**
     * @return 由于是分析系统  所以首页面直接返回到  登录页面 暂时没有主页面
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String toIndex(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        //当前用户的消息
        List<MsgInfo> msgInfos = msgService.getMsgByToSIdAndMRead(staff.getSId(), Constant.MARK_UNREAD);
        model.addAttribute(Constant.MODEL_KEY_UNREAD_MESSAGE, msgInfos);

        //未读消息的个数
        model.addAttribute(Constant.MODEL_KEY_UNREAD_MESSAGE_COUNT, msgInfos.size());

        return "home";
    }

    /**
     * @return
     */
    @RequestMapping(value = "/unauthorized")
    public String toUnauthorized() {

        return "unauthorized";
    }

    @RequestMapping(value = "/aboutus")
    public String toAboutUs(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        return "aboutus";
    }

    @RequestMapping(value = "/aboutsystem")
    public String toAboutSystem(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        return "aboutsystem";
    }
}
