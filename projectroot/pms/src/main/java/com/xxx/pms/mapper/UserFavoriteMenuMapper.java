package com.xxx.pms.mapper;

import com.xxx.pms.entity.UserFavoriteMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface UserFavoriteMenuMapper extends Mapper<UserFavoriteMenu> {
    /**
     * 查询菜单id集合通过用户id
     * @param usrId 用户id
     * @return   菜单id集合
     */
    @Select("select menu_id from sys_user_favorite_menu where  user_id=#{usrId}")
    List<Integer> selectMenuIdsByUserId(Integer usrId);

    /**
     * 通过菜单id删除数据
     * @param menuId 菜单id
     * @return 删除结果
     */
    @Delete("delete from sys_user_favorite_menu where menu_id=#{menuId}")
    int deleteByMenuId(Integer menuId);
}
