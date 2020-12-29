package com.xxx.pms.service;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Company;
import com.xxx.pms.entity.CompanyMenu;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.po.RequestParamPage;

import java.util.List;
import java.util.Map;


/**
 * 公司服务类
 */
public interface CompanyService {

    Map add(Company company);

    int deleteById(Integer id);

    int updateById(Company company);

    Company selectById(Integer id);

    PageInfo<Company> selectByPageCondition(RequestParamPage<Company> paramPage);

    /**
     * 公司新增菜单
     * @param companyMenu 公司菜单实体类
     * @return 新增结果
     */
    int addMenu(CompanyMenu companyMenu);

    /**
     * 公司删除菜单
     * @param companyMenu 公司菜单实体类
     * @return 删除结果
     */
    int deleteMenu(CompanyMenu companyMenu);

    /**
     * 通过公司id查询公司菜单
     * @param companyId  公司id
     * @return 公司菜单集合
     */
    List<Menu> getCompanyMenus(Integer companyId);

    /**
     * 查询公司-公司中包含公司的菜单信息
     * @param companyId 公司id
     * @return 公司
     */
    Company getCompanyContainMenus(Integer companyId);

    /**
     * 公司新增菜单
     * @param companyId 公司id
     * @param menuIds 菜单id集合
     * @return 新增结果
     */
    int addCompanyMenus(Integer companyId, List<Integer> menuIds);
}
