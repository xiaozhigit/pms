package com.xxx.pms.service;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.entity.Role;
import com.xxx.pms.entity.UserFavoriteMenu;
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
     * @return 用户菜单集合
     */
    List<Menu> selectMenusByUserId(Integer usrId);


    /**
     * 通过用户id查询用户收藏菜单
     * @param usrId 用户id
     * @return 用户收藏菜单集合
     */
    List<Menu> selectFavoriteMenusByUserId(Integer usrId);

    /**
     * 条件查询
     * @param name 菜单名称
     * @param description 菜单描述
     * @return 菜单集合
     */
    List<Menu> selectByCondition(String name, String description);

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
}
