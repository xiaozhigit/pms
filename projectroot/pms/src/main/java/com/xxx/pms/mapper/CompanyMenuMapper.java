package com.xxx.pms.mapper;

import com.xxx.pms.entity.CompanyMenu;
import com.xxx.pms.entity.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface CompanyMenuMapper extends Mapper<CompanyMenu> {
    /**
     * 通过菜单id删除公司菜单
     * @param menuId 菜单id
     * @return 删除结果
     */
    @Delete("delete  from sys_company_menu where menu_id=#{menuId}")
    int deleteByMenuId(Integer menuId);

    /**
     * 通过公司id和菜单id删除公司菜单
     * @param companyMenu 公司菜单实体类
     * @return 删除结果
     */
    @Delete("delete  from sys_company_menu where company_id=#{companyId} and menu_id=#{menuId}")
    int deleteByCompanyIdAndMenuId(CompanyMenu companyMenu);
    @Select(" select  * from sys_menu where id in ( select menu_id from sys_company_menu where company_id=#{companyId})")
    List<Menu> getCompanyMenusByCompanyId(Integer companyId);
}
