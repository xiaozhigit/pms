package com.xxx.pms.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.*;
import com.xxx.pms.mapper.*;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.service.MenuService;
import com.xxx.pms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单服务实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private UserService userService;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private UserFavoriteMenuMapper userFavoriteMenuMapper;

    @Resource
    private CompanyMenuMapper companyMenuMapper;


    @Override
    public int add(Menu menu) {
        return menuMapper.insertSelective(menu);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        companyMenuMapper.deleteByMenuId(id);
        roleMenuMapper.deleteByMenuId(id);
        userFavoriteMenuMapper.deleteByMenuId(id);
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu selectById(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectAll() {
        Example example=new Example(Menu.class);
        example.orderBy("sort").asc();
        return menuMapper.selectByExample(example);
    }

    @Override
    public List<Menu> selectMenusByUserId(Integer usrId) {
        //查询用户角色
        User user=userService.getUserById(usrId);
        //根据角色查询其拥有的菜单id
        List<Integer> menuIds=roleMenuMapper.selectRoleMenuByRoleId(user.getRoleId());
        //根据菜单id查询菜单
        return selectMenusByMenuIds(menuIds);
    }

    @Override
    public List<Menu> selectFavoriteMenusByUserId(Integer usrId) {
        List<Integer> menuIds=userFavoriteMenuMapper.selectMenuIdsByUserId(usrId);
        return selectMenusByMenuIds(menuIds);
    }

    @Override
    public List<Menu> selectByCondition(String name, String description) {
        return null;
    }

    @Override
    public PageInfo<Menu> selectByPage(RequestParamPage<Role> parameter) {
        return null;
    }

    /**
     * 通过菜单id集合查询菜单集合
     * @param menuIds 菜单id集合
     * @return 菜单集合
     */
    public List<Menu> selectMenusByMenuIds(List<Integer> menuIds) {
       return menuMapper.selectMenusByIds(menuIds);
    }

    @Override
    public int userFavoriteMenu(UserFavoriteMenu userFavoriteMenu) {
        return userFavoriteMenuMapper.insert(userFavoriteMenu);
    }
}
