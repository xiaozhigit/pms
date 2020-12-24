package com.xxx.pms.service;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Role;
import com.xxx.pms.po.RequestParamPage;

import java.util.List;

/**
 * 角色服务类
 */
public interface RoleService {
    int add(Role role);

    int deleteById(Integer id);

    int updateById(Role role);

    Role selectById(Integer id);

    List<Role> selectAll();

    /**
     * 条件查询
     * @param name 角色名称
     * @param description 角色描述
     * @return 角色集合
     */
    List<Role> selectByCondition(String name, String description);

    /**
     * 分页查询
     * @param parameter 查询参数
     * @return 分页对象
     */
    PageInfo<Role> selectByPage(RequestParamPage<Role> parameter);
}
