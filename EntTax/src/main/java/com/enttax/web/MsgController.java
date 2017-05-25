package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by brainy on 17-5-24.
 */
@Controller
@RequestMapping(value = "/msg")
public class MsgController extends BaseController {

    @Autowired
    private MsgService msgService;

    /**
     * 拿到这个用户对应的所有
     *
     * @param model
     * @param sId
     * @return
     */
    @RequestMapping(value = "/allmsg", method = RequestMethod.GET)
    public String toMsg(Model model,
                        @RequestParam(value = "sid") String sId) {

        model.addAttribute("msgs", msgService.getMsgByToSId(sId));

        return "msg/allmsg.html";
    }

    @RequestMapping(value = "/markread", method = RequestMethod.GET)
    public String markReadByMIds(Model model,
                                 @RequestParam(value = "mid", required = false) String[] mIds) {

        int result = msgService.markReadByMIds(mIds);

        return "msg/allmsg.html";
    }

    @RequestMapping(value = "/deletemsg", method = RequestMethod.GET)
    public String deleteByMIds(Model model,
                               @RequestParam(value = "mid", required = false) String[] mIds) {

        int result = msgService.deleteByMIds(mIds);

        return "msg/allmsg.html";
    }

    @RequestMapping(value = "/sendmsg", method = RequestMethod.POST)
    public String sendMsg(Model model,
                          @RequestParam(value = "tosid") String toSId,
                          @RequestParam(value = "content") String mContent) {

        //拿到登录用户的信息
        Staff staff = (Staff) session.getAttribute(Constant.CURRENT_LOGIN_STAFF_KEY);

        //发送信息
        int resutlt = msgService.sendMsg(toSId, mContent, staff.getSId());

        return "msg/allmsg.html";
    }
}
