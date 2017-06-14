package com.enttax.service.impl;

import com.enttax.dao.RoleMapper;
import com.enttax.dao.StaffMapper;
import com.enttax.model.Role;
import com.enttax.model.Staff;
import com.enttax.service.SecurityService;
import com.enttax.service.StaffService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.EnumSex;
import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolRandoms;
import com.enttax.util.tools.ToolString;
import com.enttax.vo.Profile;
import com.enttax.vo.StaffInfo;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by brainy on 17-4-26.
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RoleMapper roleMapper;

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


    /**
     * 通过电话号码注册一个员工
     * @param sPhone
     * @param role
     * @return
     */
    @Transactional
    @Override
    public int addStaff(String sPhone, String role) {
        int result;
        //添加员工
        Staff staff = new Staff();
        String sId = ToolRandoms.randomCode8();

        //拿到加密后的密码和摘要
        Map map = securityService.encodePassword("dbroom1411", sId);
        String sPassword = (String) map.get(SecurityService.ENCODE_RESULT_KEY_PASSWORD);
        String Ssalt = (String) map.get(SecurityService.ENCODE_RESULT_KEY_SALT);

        try { //电话号码为为唯一键，捕捉异常

            staff.setSId(sId);
            staff.setSName("李华");
            staff.setSPassword(sPassword);
            staff.setSSalt(Ssalt);
            staff.setSEnter(new Date());
            staff.setSMark(0);
            staff.setSPhone(sPhone);
            staff.setSAvatar("../../img/avatar.jpg");

            result = staffMapper.insert(staff);
            if (result == 0) {
                return 0;
            }

            //添加角色
            Role role1 = new Role();
            String rId = ToolRandoms.randomId4();
            role1.setRId(rId);
            role1.setRName(role);
            role1.setRMark(1);
            role1.setRUpdateTime(new Date());

            result = roleMapper.insert(role1);
            if (result == 0) {
                return 0;
            }

            //添加员工与角之间的关系
            result = staffMapper.insertStaffAndRoleRelation(sId, rId);

            return result;

        }catch (Exception e){
            return 0;
        }
    }

    /**
     * 显示所有员工信息
     * @return
     */
    @Override
    public List<StaffInfo> selectAllStaffInfo() {

        return  staffMapper.selectAllStaffInfo();
    }

    /**
     * 通过sid删除员工的信息
     * @param sId
     * @return
     */
    @Transactional
    @Override
    public int deleteStaffBySid(String sId) {

        try {
            //先去中间表查出sId 对应的  rId
            String rid = staffMapper.selectStaffRoleId(sId);
            if (rid != null) {
                //先删除中间表
                staffMapper.deleteStaffRole(sId);
                //删除role表
                roleMapper.deleteByPrimaryKey(rid);
                //假删除  将用户的SMark字段设置为-1
                Staff staff=staffMapper.selectByPrimaryKey(sId);
                staff.setSMark(-1);

                return staffMapper.updateByPrimaryKey(staff);

            }
            return 0;
        }catch (Exception e){
            return 0;
        }

    }

    /**
     * 通过sid更新员工的角色
     * @param sId
     * @param rName
     * @return
     */
    @Transactional
    @Override
    public int updateStaffForRole(String sId, String rName) {

        return  roleMapper.updateStaffForRole(sId,rName);
    }
}
