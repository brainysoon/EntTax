package com.enttax.service.impl;

import com.enttax.dao.PermsMapper;
import com.enttax.model.Perms;
import com.enttax.model.Role;
import com.enttax.service.PermsService;
import com.enttax.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brainy on 17-4-26.
 */
@Service
public class PermsServiceImpl implements PermsService {

    @Autowired
    private PermsMapper permsMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public List<Perms> selectByRId(String rId) {
        return permsMapper.selectByRId(rId);
    }

    @Override
    public List<Perms> listPermsBySId(String sId) {

        List<Role> roles = roleService.selectBySId(sId);
        List<Perms> permses = new ArrayList<>();

        //查找 当前用户的所有角色的权限
        for (Role role : roles) {

            permses.addAll(selectByRId(role.getRId()));
        }

        return permses;
    }

    @Override
    public List<String> listPermsNameBySId(String sId) {

        List<Perms> permses = listPermsBySId(sId);
        List<String> names = new ArrayList<>();

        //将角色名字全部列举出来
        for (Perms perms : permses) {

            names.add(perms.getPName());
        }

        return names;
    }
}
