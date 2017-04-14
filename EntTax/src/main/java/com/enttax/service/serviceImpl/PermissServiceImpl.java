package com.enttax.service.serviceImpl;

import com.enttax.dao.RoleMapper;
import com.enttax.dao.StaffMapper;
import com.enttax.model.Role;
import com.enttax.model.Staff;
import com.enttax.service.PermissService;
import com.enttax.util.constant.ConstantStr;
import com.enttax.util.tools.Encodes;
import com.enttax.util.tools.ToolDates;
import com.enttax.util.tools.ToolString;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by lcyanxi on 17-3-13.
 */
@Service
public class PermissServiceImpl implements PermissService {
    private static final Logger logger=Logger.getLogger(PermissServiceImpl.class);
   @Resource
    private StaffMapper staffMapper;
   @Resource
   private RoleMapper roleMapper;

    /** 注册
     * 需要校验电话号码和邮箱的唯一性
     * @param staff
     * @return
     */
    @Transactional
    public boolean register(Staff staff,String rid) {
        List<Staff> list;
        try {
            list = staffMapper.selectByEmail(staff.getSemail());
           if (!ToolString.isEmpty(list)){
               return false;
           }
           list = staffMapper.selectByPhone(staff.getSphone());
           if (!ToolString.isEmpty(list)){
               return false;
           }

           Role role=roleMapper.selectByPrimaryKey(rid);
           if (role==null){
               return false;
           }

            String sid= ToolDates.getDate8Num();
            staff.setSid(sid);
            staff.setSenter(new Date());
            staff.setSpassword(Encodes.encodeBase64(staff.getSpassword()));

           staffMapper.insert(staff);
            //将用户和角色的关系插入中间表中
           staffMapper.insertStaffAndRoleRelation(staff.getSid(),rid);

           logger.info("－－－－－－－用户添加成功－－－－－－－");
           logger.debug("----------debug用户添加成功－－－－");
            return  true;
        }catch (Exception e){
            logger.info("－－－－－－添加用户出错了－－－－－－－");
            logger.debug("----------debug添加用户出错了－－－－");
            throw  new RuntimeException(e);
        }

    }

    /**登录
     * 可能方式为用户名+密码、电话号码+密码、邮箱+密码
     * @param sname
     * @param password
     * @return
     */
    public Staff login(String sname, String password) {
        Staff staff;
        if(ToolString.isPhoneNumber(sname)){
            staff= staffMapper.selectByPhoneAndPassword(sname,password);
        }
        else if (ToolString.isEmail(sname)){
            staff= staffMapper.selectByEmailAndPassword(sname,password);
        }else {
            staff= staffMapper.selectByNameAndPassword(sname,password);
        }
        logger.info("login is successful!!");
        return staff;
    }

    /**
     * 更新用户信息
     * @param staff
     * @return
     */
    public int updateStaffInfo(Staff staff) {
        return staffMapper.updateByPrimaryKey(staff);
    }

    /**
     * 通过电话号码查找用户是否存在
     * @param phone
     * @param request
     * @return
     */
    public boolean selectByPhone(String phone, HttpServletRequest request) {
        List<Staff> list=staffMapper.selectByPhone(phone);
        System.out.println("listsize:"+list.size());
        if (ToolString.isEmpty(list)){
            return false;
        }
        for (Staff staff : list) {
            request.getSession().setAttribute(ConstantStr.SID,staff.getSid());
            System.out.println("sessionSet:"+staff.getSid());
        }
        return true;
    }

    /**
     * 重置密码
     * @param sid
     * @param newPasswod
     * @return
     */
    public boolean updateToPassword(String sid,String newPasswod) {
        return staffMapper.updateToPassword(sid,newPasswod)>0?true:false;
    }

    public Staff selectByUserName(String username) {
        staffMapper.selectByUserName(username);
        return null;
    }
}
