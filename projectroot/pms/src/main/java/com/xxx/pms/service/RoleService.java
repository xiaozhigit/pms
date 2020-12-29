package com.xxx.pms.service;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.entity.Role;
import com.xxx.pms.entity.RoleMenu;
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

    /**
     * 角色添加菜单
     * @param roleMenu 角色菜单
     * @return 新增结果
     */
    int roleAddMenu(RoleMenu roleMenu);

    /**
     * 角色删除菜单
     * @param roleMenu 角色菜单
     * @return 删除结果
     */
    int roleDeleteMenu(RoleMenu roleMenu);

    /**
     * 获取角色拥有的菜单
     * @param roleId 角色id
     * @return 角色菜单集合
     */
    List<Menu> getRoleMenu(String roleId);

    /**
     * 角色添加菜单
     * @param menuIds 菜单id集合
     * @return 添加结果
     */
    int roleAddMenus(Integer roleId,List<Integer> menuIds);

    /**
     * 通过公司id获取 公司角色
     * @param companyId 公司id
     * @return 公司角色集合
     */
    List<Role> getCompanyRoles(Integer companyId);
}
