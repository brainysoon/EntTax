package com.enttax.service.permissionService.permissionImpl;

import com.enttax.dao.StaffMapper;
import com.enttax.model.Staff;
import com.enttax.service.permissionService.PermissService;
import com.enttax.util.tools.ToolString;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by lcyanxi on 17-3-13.
 */
@org.springframework.stereotype.Service
public class PermissServiceImpl implements PermissService {
   @Resource
    private StaffMapper staffMapper;

    /** 注册
     * 需要校验电话号码和邮箱的唯一性
     * @param staff
     * @return
     */
    public boolean register(Staff staff) {
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

           staffMapper.insert(staff);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
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
        return staff;
    }

    public int updateStaffInfo(Staff staff) {
        return staffMapper.updateByPrimaryKey(staff);
    }

    public Staff findByPassword(String phone) {
         List<Staff> list=staffMapper.selectByPhone(phone);
         if (ToolString.isEmpty(list)){
             return null;
         }

        return null;
    }

    public boolean selectByPhone(String phone, HttpServletRequest request) {
        List<Staff> list=staffMapper.selectByPhone(phone);
        if (ToolString.isEmpty(list)){
            return false;
        }
        for (Staff staff : list) {
            request.getSession().setAttribute("sid",staff.getSid());
        }
        return true;
    }
}
