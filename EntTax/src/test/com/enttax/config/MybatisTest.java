package com.enttax.config;

import com.enttax.dao.BillMapper;
import com.enttax.dao.PermsMapper;
import com.enttax.dao.RoleMapper;
import com.enttax.dao.StaffMapper;
import com.enttax.model.Bill;
import com.enttax.model.Perms;
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
        staff.setSId(ToolDates.getDate8Num());
        staff.setSPassword(Encodes.encodeBase64("666666"));
        staff.setSBirthday(new Date());
        staff.setSEnter(new Date());
        staff.setSEmail("2757710657@qq.com");
        staff.setSName("卡伦");
        staffMapper.insert(staff);

       RoleMapper roleMapper= context.getBean(RoleMapper.class);
        Role role= roleMapper.selectByPrimaryKey("1003");
        System.out.println("role"+role);

        staffMapper.insertStaffAndRoleRelation(staff.getSId(),role.getRId());

    }

    @Test
    public void testPerm_RoleTest(){
        RoleMapper roleMapper=context.getBean(RoleMapper.class);
//        roleMapper.insertPerm_Role("1001","1002");
    }

    @Test
    public void permissionTest(){
       PermsMapper permissionMapper= context.getBean(PermsMapper.class);
        Perms perms =new Perms();
        perms.setPId("1006");
        perms.setPName("操作权限");
        perms.setPType(5);
       permissionMapper.insert(perms);
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
            System.out.println(staff.getSName());
        }

        PageHelper.startPage(1,5);
        List<Bill> bills= billMapper.selectAll();

        for (Bill bill:bills) {
//            System.out.println(bill.getBTitle());

        }
    }

    @Test
    public void MoreToMoreTest(){
        StaffMapper staffMapper=context.getBean(StaffMapper.class);
//        List<Staff> staffs = staffMapper.selectByUserName("邓小平");
//        List<Staff> staffs=staffMapper.selectByUserName("邓小平");
//        System.out.println(staff.getSname());
//        System.out.println(staff.getRoles().size());
//        for (Staff staff:staffs){
//            System.out.println(staff.getSname());
//            System.out.println(staff.getSemail());
//            List<Role> roles=staff.getRoles();
//            for (Role role:roles){
//                System.out.println(role.getRname());
//                System.out.println(role.getRid());
//                System.out.println(role.getRupdatetime());
//                List<Perms> permses =role.getPermses();
//                for (Perms perms : permses){
//                    System.out.println(perms.getPname());
//                }
//            }
//        }
    }

    @Test
    public void  seleteStaffRoleTest(){
        StaffMapper staffMapper=context.getBean(StaffMapper.class);
        String rid=staffMapper.selectStaffRoleId("20174723");
        System.out.println(rid);

    }

    @Test
    public void updateStaffForRoleTest(){
        RoleMapper roleMapper=context.getBean(RoleMapper.class);
        int result=roleMapper.updateStaffForRole("20177128","操作员");
        System.out.println(result);

    }

}