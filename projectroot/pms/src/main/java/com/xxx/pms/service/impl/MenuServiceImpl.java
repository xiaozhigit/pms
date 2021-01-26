package com.xxx.pms.service.impl;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.*;
import com.xxx.pms.mapper.*;
import com.xxx.pms.response.MenuDto;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.service.CompanyService;
import com.xxx.pms.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单服务实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private UserFavoriteMenuMapper userFavoriteMenuMapper;

    @Resource
    private CompanyMenuMapper companyMenuMapper;

    @Resource
    private CompanyService companyService;

    @Transactional
    @Override
    public int add(Menu menu) {
        menuMapper.insertSelective(menu);
        //新增菜单时，公司管理员公布新增菜单
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setMenuId(menu.getId());
        Role role=companyService.getCompanyAdminRole(0);
        roleMenu.setRoleId(role.getId());
        roleMenuMapper.insert(roleMenu);
        CompanyMenu companyMenu=new CompanyMenu();
        companyMenu.setCompanyId(0);
        companyMenu.setMenuId(menu.getId());
        return companyMenuMapper.insert(companyMenu);
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
    public List<MenuDto> selectMenusByUserId(Integer userId) {
        List<Menu> menus=menuMapper.selectMenusByUserId(userId);
        List<Menu> favoriteMenu=menuMapper.queryFavoriteMenusByUserId(userId);
        for(Menu menu:menus){
            for (Menu favorMenu:favoriteMenu) {
                if(menu.getId().equals(favorMenu.getId())){
                    menu.setIsFavorite(true);
                }
            }
        }
       return  buildTreeAll(menus,getTopMenus(menus));
    }

    @Override
    public List<Menu> selectFavoriteMenusByUserId(Integer userId) {
            return  menuMapper.queryFavoriteMenusByUserId(userId);
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
        return userFavoriteMenu.getIsFavorite()?userFavoriteMenuMapper.insert(userFavoriteMenu):userFavoriteMenuMapper.delete(userFavoriteMenu);
    }

    @Override
    public List<Menu> getAllMenusContainCompanyName() {
        return menuMapper.selectAllByOrder();
    }

    /**
     * 获取顶级菜单
     * @param menus 菜单集合
     * @return 顶级菜单集合
     */
    private List<Menu> getTopMenus(List<Menu> menus){
        List<Menu> topMenus=new ArrayList<>();
        for (Menu m :menus) {
            if(m.getParentId()==null){
                topMenus.add(m);
            }
        }
        return topMenus;
    }

    /**
     * 查找菜单的子菜单
     * @param menu 菜单
     * @param menus 所有菜单集合
     * @return  子菜单
     */
    private List<Menu> findChildren(Menu menu,List<Menu> menus){
        List<Menu> childrenMenu=new ArrayList<>();
        for (Menu me :menus) {
            if(me.getParentId()!=null&&me.getParentId().equals(menu.getId())){
                childrenMenu.add(me);
            }
        }
        return childrenMenu;
    }

    /**
     * 构建树形
     * @param allMenus 所有菜单
     * @param menus 上级菜单
     * @return 菜单树
     */
    private List<MenuDto> buildTreeAll(List<Menu> allMenus, List<Menu> menus) {
        List<MenuDto> menuDtos = new ArrayList<>();
        for (Menu menu : menus) {
            MenuDto menuDto = new MenuDto();
            menuDto.setMenu(menu);
            menuDto.setChildren(buildTreeAll(allMenus, findChildren(menu, allMenus)));
            menuDtos.add(menuDto);
        }
        return menuDtos;
    }
}
