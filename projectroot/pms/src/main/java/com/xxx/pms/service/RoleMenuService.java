package com.xxx.pms.service;

import com.xxx.pms.entity.RoleMenu;

/**
 * 角色菜单服务类
 */
public interface RoleMenuService {

    int add(RoleMenu roleMenu);

    int deleteByRoleIdAndMenuId(RoleMenu roleMenu);

    int delete(RoleMenu roleMenu);
}
