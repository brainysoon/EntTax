package com.enttax.service.permissionService.permissionImpl;

import com.enttax.dao.StaffMapper;
import com.enttax.model.Staff;
import com.enttax.service.permissionService.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by lcyanxi on 17-3-13.
 */
@Service
public class RegisterServiceImpl implements RegisterService {
   @Resource
    private StaffMapper staffMapper;

    public boolean register(Staff staff) {
        try {
            staffMapper.insert(staff);
            System.out.println("save success!!");
        }catch (Exception e){
            System.out.println("save failure!!!");
            e.printStackTrace();
        }

        return false;
    }
}
