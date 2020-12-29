package com.xxx.pms.service.impl;

import com.xxx.pms.entity.RoleMenu;
import com.xxx.pms.mapper.RoleMenuMapper;
import com.xxx.pms.service.RoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleMenuServiceImpl  implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;


    @Override
    public int add(RoleMenu roleMenu) {
        return roleMenuMapper.insert(roleMenu);
    }

    @Override
    public int deleteByRoleIdAndMenuId(RoleMenu roleMenu) {
        return roleMenuMapper.deleteByRoleIdAndMenuId(roleMenu);
    }
}
