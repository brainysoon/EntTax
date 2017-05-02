package com.enttax.service.impl;

import com.enttax.dao.RoleMapper;
import com.enttax.model.Role;
import com.enttax.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brainy on 17-4-26.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectBySId(String sId) {
        return roleMapper.selectBySId(sId);
    }

    @Override
    public List<String> listRoleNameBySId(String sId) {

        List<Role> roles = selectBySId(sId);
        List<String> names = new ArrayList<>();

        //遍历 添加  所有的角色的名称
        for (Role role : roles) {

            names.add(role.getRName());
        }

        return names;
    }
}
