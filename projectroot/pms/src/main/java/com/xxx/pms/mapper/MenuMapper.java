package com.xxx.pms.mapper;

import com.xxx.pms.entity.Menu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@Repository
public interface MenuMapper extends Mapper<Menu> {
    /**
     * 通过菜单id查询菜单
     * @param menuIds  菜单id集合
     * @return 菜单集合
     */
    List<Menu> selectMenusByIds(List<Integer> menuIds);

    /**
     * 通过用户id查询用户的菜单集合
     * @param userId 用户id
     * @return 菜单集合
     */
    @Select("select * from sys_menu where id in(select menu_id from  sys_role_menu where role_id=(select role_id from sys_user where id=#{userId}))")
    List<Menu> selectMenusByUserId(Integer userId);

    /**
     * 通过用户id查询用户收藏的菜单集合
     * @param userId 用户id
     * @return 菜单那集合
     */
    @Select("select * from sys_menu where id in(select menu_id from sys_user_favorite_menu where user_id=#{userId})")
    List<Menu> queryFavoriteMenusByUserId(Integer userId);

}
