package com.xxx.pms.service;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.entity.Role;
import com.xxx.pms.entity.UserFavoriteMenu;
import com.xxx.pms.response.MenuDto;
import com.xxx.pms.po.RequestParamPage;

import java.util.List;

/**
 * 菜单服务类
 */
public interface MenuService {
    int add(Menu menu);

    int deleteById(Integer id);

    int updateById(Menu menu);

    Menu selectById(Integer id);

    List<Menu> selectAll();

    /**
     * 通过用户id查询用户菜单
     * @param usrId 用户id
     * @return 用户菜单集合树形
     */
    List<MenuDto> selectMenusByUserId(Integer usrId);


    /**
     * 通过用户id查询用户收藏菜单
     * @param usrId 用户id
     * @return 用户收藏菜单集合
     */
    List<Menu> selectFavoriteMenusByUserId(Integer usrId);

    /**
     * 分页查询
     * @param parameter 查询参数
     * @return 分页对象
     */
    PageInfo<Menu> selectByPage(RequestParamPage<Role> parameter);

    /**
     * 用户收藏菜单
     * @param userFavoriteMenu 用户收藏菜单实体类
     * @return 收藏结果
     */
    int userFavoriteMenu(UserFavoriteMenu userFavoriteMenu);

    /**
     * 查询所有菜单，菜单中包含公司名称
     * @return 菜单集合
     */
    List<Menu> getAllMenusContainCompanyName();
}
