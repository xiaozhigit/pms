package com.xxx.pms.mapper;

import com.xxx.pms.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface RoleMenuMapper extends Mapper<RoleMenu> {
    /**
     * 通过角色id查询菜单id集合
     * @param roleId
     * @return
     */
    @Select("select menu_id from  sys_role_menu where role_id=#{roleId}")
    List<Integer> selectRoleMenuByRoleId(Integer roleId);

    /**
     * 通过菜单id删除数据
     * @param menuId  菜单id
     */
    @Delete("delete  from sys_role_menu where menu_id=#{menuId}")
    int deleteByMenuId(Integer menuId);
}
