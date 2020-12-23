package com.xxx.pms.mapper;

import com.xxx.pms.entity.CompanyMenu;
import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface CompanyMenuMapper extends Mapper<CompanyMenu> {
    /**
     * 通过菜单id删除公司菜单
     * @param menuId 菜单id
     * @return 删除结果
     */
    @Delete("delete  from sys_company_menu where menu_id=#{menuId}")
    int deleteByMenuId(Integer menuId);
}
