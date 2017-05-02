package com.enttax.service;

import com.enttax.model.Role;

import java.util.List;

/**
 * Created by brainy on 17-4-26.
 */
public interface RoleService {

    /**
     * @param sId
     * @return
     */
    List<Role> selectBySId(String sId);

    /**
     * @param sId
     * @return
     */
    List<String> listRoleNameBySId(String sId);
}
