package com.enttax.web;

import com.enttax.model.Staff;
import com.enttax.service.MsgService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.vo.MsgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by brainy on 17-5-24.
 */
@Controller
@RequestMapping(value = "/staff")
public class MsgController extends BaseController {

    @Autowired
    private MsgService msgService;

    /**
     * 拿到这个用户对应的所有
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/message", method = RequestMethod.GET)
    public String toMsg(Model model) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        //拿到所有的消息
        List<MsgInfo> msgs = msgService.getMsgByToSId(staff.getSId());
        model.addAttribute(Constant.MODEL_KEY_ALL_MESSAGE, msgs);

        //拿到已读消息
        List<MsgInfo> readMsgs = msgService.getMsgByToSIdAndMRead(staff.getSId(), Constant.MARK_READ);
        model.addAttribute(Constant.MODEL_KEY_READ_MESSAGE, readMsgs);

        //拿到未读消息
        List<MsgInfo> unreadMsgs = msgService.getMsgByToSIdAndMRead(staff.getSId(), Constant.MARK_UNREAD);
        model.addAttribute(Constant.MODEL_KEY_UNREAD_MESSAGE, unreadMsgs);

        return "inform";
    }

    @RequestMapping(value = "/message/markread", method = RequestMethod.POST)
    public String markReadByMIds(Model model,
                                 @RequestParam(value = "mid", required = false) String[] mIds) {

        if (mIds != null) {

            int result = msgService.markReadByMIds(mIds);
        }

        return "redirect:/staff/message";
    }

    @RequestMapping(value = "/sendmessage", method = RequestMethod.POST)
    public String sendMsg(Model model,
                          @RequestParam(value = "tosid") String toSId,
                          @RequestParam(value = "content") String mContent) {

        //拿到登录用户的信息
        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        try {

            //发送信息
            int resutlt = msgService.sendMsg(toSId, mContent, staff.getSId());

            if (resutlt > 0) {

                model.addAttribute(ConstantStr.MESSAGE, "发送成功!");
            } else {

                model.addAttribute(ConstantStr.MESSAGE, "发送失败!");
            }

        } catch (Exception ex) {

            ex.printStackTrace();
            model.addAttribute(ConstantStr.MESSAGE, "收件人员工号错误!");
        }

        return "sendmsg";
    }

    @RequestMapping(value = "/sendmessage", method = RequestMethod.GET)
    public String toSendMsg(Model model) {

        //拿到登录用户的信息
        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        model.addAttribute(ConstantStr.STAFFINFO, staff);

        return "sendmsg";
    }

    @RequestMapping(value = "/delete/msg/{mid}")
    public String deleteByMId(@PathVariable(value = "mid") String mid) {

        int result = msgService.deleteByMIds(new String[]{mid});

        return "redirect:/staff/message";
    }

    @RequestMapping(value = "/message/delete/all/{index}")
    public String deleteAll(@PathVariable(value = "index") Integer index) {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);

        int result = msgService.deleteByToSId(staff.getSId(), index);

        return "redirect:/staff/message";
    }

    @RequestMapping(value = "/message/markread/all", method = RequestMethod.GET)
    public String markReadAll() {

        //用户登录信息
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);

        int result = msgService.markReadAllByToSId(staff.getSId());

        return "redirect:/";
    }
}
