package com.enttax.service.permissionService.permissionImpl;

import com.enttax.dao.StaffMapper;
import com.enttax.model.Staff;
import com.enttax.service.permissionService.PermissService;

import javax.annotation.Resource;

/**
 * Created by lcyanxi on 17-3-13.
 */
@org.springframework.stereotype.Service
public class PermissServiceImpl implements PermissService {
   @Resource
    private StaffMapper staffMapper;

    public boolean register(Staff staff) {
        try {
           System.out.println(staffMapper.insert(staff));
            System.out.println("save success!!");
            return  true;
        }catch (Exception e){
            System.out.println("save failure!!!");
            e.printStackTrace();
            return false;
        }

    }

    public Staff login(String sname, String password) {
        return staffMapper.selectByNameAndPassword(sname, password);
    }

    public int updateStaffInfo(Staff staff) {
        int key =staffMapper.updateByPrimaryKey(staff);
        if (key>0){
            System.out.println("save success!!");
        }
        return 0;
    }
}
