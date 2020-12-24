package com.xxx.pms.mapper;

import com.xxx.pms.entity.Menu;
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
}
