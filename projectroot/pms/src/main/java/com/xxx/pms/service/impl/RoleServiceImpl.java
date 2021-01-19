package com.xxx.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.entity.Role;
import com.xxx.pms.entity.RoleMenu;
import com.xxx.pms.mapper.RoleMapper;
import com.xxx.pms.mapper.RoleMenuMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.service.RoleService;
import com.xxx.pms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserService userService;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public int add(Role role) {
        return roleMapper.insertSelective(role);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        //将已使用该角色的用户的角色设置为空
        Role role=selectById(id);
        userService.updateUserRoleIdByCompanyIdAndRoleId(role.getCompanyId(),role.getId());
        //删除角色的菜单
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setRoleId(id);
        roleMenuMapper.delete(roleMenu);
        //删除角色数据
        return roleMapper.deleteByPrimaryKey(id);

    }

    @Override
    public int updateById(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Role> selectByCondition(String name, String description) {
        Example example = new Example(Role.class);
        example.selectProperties("name", name);
        example.selectProperties("description", description);
        return roleMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Role> selectByPage(RequestParamPage<Role> parameter) {
        PageHelper.startPage(parameter.getPage(),parameter.getPageSize());
        return new PageInfo<>(roleMapper.selectByCondition(parameter.getParam()));
    }

    @Override
    public int roleAddMenu(RoleMenu roleMenu) {
        return roleMenuMapper.insert(roleMenu);
    }

    @Override
    public int roleDeleteMenu(RoleMenu roleMenu) {
        return roleMenuMapper.deleteByRoleIdAndMenuId(roleMenu);
    }

    @Override
    public List<Menu> getRoleMenu(String roleId) {
        return roleMenuMapper.selectRoleMenuByRoleId(roleId);
    }

    @Override
    public int roleAddMenus(Integer roleId,List<Integer> menuIds) {
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setRoleId(roleId);
        roleMenuMapper.delete(roleMenu);
        for (Integer menuId: menuIds) {
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
        return 1;
    }

    @Override
    public List<Role> getCompanyRoles(Integer companyId) {
        return roleMapper.getCompanyRoles(companyId);
    }

    @Override
    public Role getCompanyAdminRole(Integer companyId) {
        return roleMapper.getCompanyAdminRole(companyId);
    }
}
