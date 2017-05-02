package com.enttax.service.impl;

import com.enttax.dao.StaffMapper;
import com.enttax.model.Staff;
import com.enttax.service.SecurityService;
import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.EnumSex;
import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolString;
import com.enttax.vo.Profile;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by brainy on 17-4-26.
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private SecurityService securityService;

    /**
     * 更新用户信息
     *
     * @param profile
     * @return
     */
    public int updateStaffInfo(Profile profile, Session session) {
        //拿到session的staff对象
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);

        // 将profile要更改的信息填充到staff对象里
        String sName = profile.getSName();
        if (sName != null || sName != "") {
            staff.setSName(profile.getSName());
        }

        String sAddress = profile.getSAddress();
        if (sAddress != null || sAddress != "") {
            staff.setSAddress(profile.getSAddress());
        }


        //将字符串的“男” “女” 转换为 true和false
        staff.setSSex(EnumSex.ToSex(profile.getSSex()));

        //将字符串的日期转换为date类型
        String sbirthday = profile.getSBirthday();
        if (sbirthday != null || sbirthday != "") {
            Date birthday = ToolDates.parseDateStr(sbirthday);
            if (birthday != null) {
                staff.setSBirthday(birthday);
            }
        }

        if (staffMapper.updateByPrimaryKey(staff) > 0) {
            session.setAttribute(ConstantStr.STAFFINFO, staff);
            return 1;
        }
        return 0;
    }

    /**
     * 更新用户信息（用于更新phone 和 更新email）
     *
     * @param staff
     * @return
     */
    @Override
    public int updateStaff(Staff staff) {
        return staffMapper.updateByPrimaryKey(staff);
    }

    @Override
    public Staff selectByPhone(String phone) {
        return staffMapper.selectByPhone(phone);
    }

    @Override
    public Staff selectByEamil(String email) {
        return staffMapper.selectByEmail(email);
    }

    @Override
    public int updatePassword(String sId, String newPasswod) {

        Map<String, String> map = securityService.encodePassword(newPasswod, sId);

        //拿到加密过后的密码  和  摘要
        String password = map.get(SecurityService.ENCODE_RESULT_KEY_PASSWORD);
        String salt = map.get(SecurityService.ENCODE_RESULT_KEY_SALT);

        return staffMapper.updatePassword(sId, password, salt);
    }

    /**
     * 更新头像
     *
     * @param realPath
     * @param session
     * @return
     */
    @Override
    public int updateAvatar(String realPath, Session session) {

        //拿到session的staff用户
        Staff staff = (Staff) session.getAttribute(ConstantStr.STAFFINFO);
        staff.setSAvatar(realPath);
        if (staffMapper.updateByPrimaryKey(staff) > 0) {
            //如果成功 更新session里的staff
            session.setAttribute(ConstantStr.STAFFINFO, staff);
            return 1;
        }
        //更新失败
        return 0;
    }

    @Override
    public Staff selectByIdentify(String sName) {

        Staff staff;
        if (ToolString.isPhoneNumber(sName)) {
            staff = staffMapper.selectByPhone(sName);
        } else if (ToolString.isEmail(sName)) {
            staff = staffMapper.selectByEmail(sName);
        } else {
            staff = staffMapper.selectByPrimaryKey(sName);
        }

        return staff;
    }

    @Override
    public Staff selectByPrimaryKey(String sId) {
        return staffMapper.selectByPrimaryKey(sId);
    }
}
