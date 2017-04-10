package com.enttax.config;

import com.enttax.dao.BillMapper;
import com.enttax.dao.PermissionMapper;
import com.enttax.dao.RoleMapper;
import com.enttax.dao.StaffMapper;
import com.enttax.model.Bill;
import com.enttax.model.Permission;
import com.enttax.model.Role;
import com.enttax.model.Staff;
import com.enttax.util.tools.Encodes;
import com.enttax.util.tools.ToolDates;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

/**
 * Created by lcyanxi on 17-3-24.
 */
public class MybatisTest {
    ApplicationContext context = new ClassPathXmlApplicationContext(
            new String[]{"spring/spring-mybatis.xml"});
    @Test
    public void testDemo() {
        StaffMapper staffMapper=context.getBean(StaffMapper.class);
        Staff staff=new Staff();
        staff.setSid(ToolDates.getDate8Num());
        staff.setSpassword(Encodes.encodeBase64("666666"));
        staff.setSbirthday(new Date());
        staff.setSenter(new Date());
        staff.setSemail("2757710657@qq.com");
        staff.setSname("卡伦");
        staffMapper.insert(staff);

       RoleMapper roleMapper= context.getBean(RoleMapper.class);
        Role role= roleMapper.selectByPrimaryKey("1003");
        System.out.println("role"+role);

        staffMapper.insertStaffAndRoleRelation(staff.getSid(),role.getRid());

    }

    @Test
    public void testPerm_RoleTest(){
        RoleMapper roleMapper=context.getBean(RoleMapper.class);
        roleMapper.insertPerm_Role("1001","1002");
    }

    @Test
    public void permissionTest(){
       PermissionMapper permissionMapper= context.getBean(PermissionMapper.class);
        Permission permission=new Permission();
        permission.setPid("1006");
        permission.setPname("操作权限");
        permission.setPtype(5);
       permissionMapper.insert(permission);
    }

    @Test
    public void mybaitsPageHelperTest(){

        StaffMapper staffMapper=context.getBean(StaffMapper.class);
        BillMapper billMapper=context.getBean(BillMapper.class);
        //紧跟着的第一个select会分页
        Page page=PageHelper.startPage(2,10);
        PageHelper.getOrderBy();

        long count=page.getTotal();
        System.out.println("count"+count);
        //测试PageInfo全部属性
        System.out.println("PageNum"+page.getPageNum());
        System.out.println("PageSize"+page.getPageSize());
        System.out.println("StartRow"+page.getStartRow());
        System.out.println("EndRow"+page.getEndRow());
        System.out.println("Total"+page.getTotal());
        System.out.println("Pages"+page.getPages());

        List<Staff> list=staffMapper.selectAll();
        System.out.println(list.size());
        for (Staff staff:list){
            System.out.println(staff.getSname());
        }

        PageHelper.startPage(1,5);
        List<Bill> bills= billMapper.selectAll();

        for (Bill bill:bills) {
            System.out.println(bill.getBtitle());

        }
    }

    @Test
    public void MoreToMoreTest(){
        StaffMapper staffMapper=context.getBean(StaffMapper.class);
//        List<Staff> staffs = staffMapper.selectByUserName("邓小平");
        List<Staff> staffs=staffMapper.selectByUserName("邓小平");
//        System.out.println(staff.getSname());
//        System.out.println(staff.getRoles().size());
        for (Staff staff:staffs){
            System.out.println(staff.getSname());
            System.out.println(staff.getSemail());
            List<Role> roles=staff.getRoles();
            for (Role role:roles){
                System.out.println(role.getRname());
                System.out.println(role.getRid());
                System.out.println(role.getRupdatetime());
                List<Permission> permissions=role.getPermissions();
                for (Permission permission:permissions){
                    System.out.println(permission.getPname());
                }
            }
        }
    }

}