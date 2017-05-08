package com.enttax.dao;

import com.enttax.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RoleMapper {

    /**
     * @param rId
     * @return
     */
    int deleteByPrimaryKey(String rId);

    /**
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * @param rId
     * @return
     */
    Role selectByPrimaryKey(String rId);

    /**
     * @return
     */
    List<Role> selectAll();

    /**
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);

    /**
     * @param sId
     * @return
     */
    List<Role> selectBySId(@Param("sId") String sId);

    /**
     * 更改员工的角色
     * @param sId
     * @param rName
     * @return
     */
    int updateStaffForRole(@Param("sId") String sId,@Param("rName") String rName);
}