package com.xxx.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.*;
import com.xxx.pms.mapper.CompanyMapper;
import com.xxx.pms.mapper.CompanyMenuMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.service.CompanyService;
import com.xxx.pms.service.RoleMenuService;
import com.xxx.pms.service.RoleService;
import com.xxx.pms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private CompanyMenuMapper companyMenuMapper;

    @Resource
    private RoleMenuService roleMenuService;


    @Override
    @Transactional
    public Map add(Company company) {
        Map resMap = new HashMap<String ,Object>();
        resMap.put("code", 200);
        //设置公司状态为正常
        company.setStatue(true);
        int result = companyMapper.insert(company);
        if (result > 0) {
            Role role = createCompanyAdminRole(company);
            //创建公司管理员用户
            Map resultMap = createCompanyAdminUser(company, role.getId());
            if ((int) resultMap.get("code") == 200) {
                //新增公司管理员用户成功，更新公司管理员信息
                Company updateCompany = new Company();
                updateCompany.setId(company.getId());
                updateCompany.setAdminId((Integer) resultMap.get("msg"));
                companyMapper.updateByPrimaryKeySelective(updateCompany);
                resMap.put("msg", "添加公司成功");
                return resMap;
            }
            return resultMap;
        }
        resMap.put("code", 400);
        resMap.put("msg", "新增失败");
        return resMap;

    }

    @Override
    public int deleteById(Integer id) {
        //设置公司状态为禁用
        Company company=new Company();
        company.setId(id);
        company.setStatue(false);
        return updateById(company) ;
    }

    @Override
    public int updateById(Company company) {
        //公司编辑管理员不变
        company.setAdminId(null);
        return companyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public Company selectById(Integer id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Company> selectByPageCondition(RequestParamPage<Company> paramPage) {
        PageHelper.startPage(paramPage.getPage(),paramPage.getPageSize());
        List<Company> companyList=companyMapper.selectByCondition(paramPage.getParam());
        return new PageInfo<>(companyList);
    }

    @Override
    @Transactional
    public int addMenu(CompanyMenu companyMenu) {
        //公司管理员角色新增菜单
        roleMenuService.add(createRoleMenuByCompanyMenu(companyMenu));
        return companyMenuMapper.insert(companyMenu);
    }

    @Override
    @Transactional
    public int deleteMenu(CompanyMenu companyMenu) {
        //公司管理员删除菜单
        roleMenuService.deleteByRoleIdAndMenuId(createRoleMenuByCompanyMenu(companyMenu));
        return companyMenuMapper.deleteByCompanyIdAndMenuId(companyMenu);
    }

    @Override
    public List<Menu> getCompanyMenus(Integer companyId) {
        return companyMenuMapper.getCompanyMenusByCompanyId(companyId);
    }

    @Override
    public Company getCompanyContainMenus(Integer companyId) {
        Company company=companyMapper.selectByPrimaryKey(companyId);
        if(company!=null){
            company.setMenus(getCompanyMenus(companyId));
            company.setRoles(getCompanyRoles(companyId));
        }
        return company;
    }
    @Transactional
    @Override
    public int addCompanyMenus(Integer companyId, List<Integer> menuIds) {
        List<Integer> deleteMenuIds = new ArrayList<>();
        List<Menu> companyMenus = getCompanyMenus(companyId);
        for (Menu companyMenu : companyMenus) {
            if (!menuIds.contains(companyMenu.getId())) {
                deleteMenuIds.add(companyMenu.getId());
            }
        }

        if(deleteMenuIds.size()>0){
            List<Role> roles = getCompanyRoles(companyId);
            RoleMenu roleMenu = new RoleMenu();
            for (Role role : roles) {
                for (Integer deleteMenuId : deleteMenuIds) {
                    roleMenu.setMenuId(deleteMenuId);
                    roleMenu.setRoleId(role.getId());
                    roleMenuService.deleteByRoleIdAndMenuId(roleMenu);
                }
            }
        }


        //清空公司所有菜单
        CompanyMenu companyMenu = new CompanyMenu();
        companyMenu.setCompanyId(companyId);
        companyMenuMapper.delete(companyMenu);
        //清空公司管理员角色所有菜单
        Integer companyAdminRoleId=getCompanyAdminRole(companyId).getId();
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setRoleId(companyAdminRoleId);
        roleMenuService.delete(roleMenu);
        //新增公司菜单及同步公司管理员菜单
        for (Integer menuId : menuIds) {
            companyMenu.setMenuId(menuId);
            companyMenuMapper.insert(companyMenu);
            roleMenu.setMenuId(menuId);
            roleMenuService.add(roleMenu);
        }
        return 1;
    }

    /**
     * 创建公司管理员角色
     * @param company 公司实体类
     * @return 创建结果
     */
    private Role createCompanyAdminRole(Company company){
        Role role=new Role();
        role.setCompanyId(company.getId());
        role.setCreateId(company.getCreateId());
        role.setCreateName(company.getCreateName());
        role.setDescription(company.getName()+"公司管理员");
        role.setName(company.getName()+"公司管理员");
        roleService.add(role);
        return role;
    }

    /**
     * 创建公司管理员
     * @param company 公司实体类
     * @param roleId 管理员id
     * @return 用户对象
     */
    private Map createCompanyAdminUser(Company company, Integer roleId){
        User user=new User();
        user.setCompanyId(company.getId());
        user.setCreateName(company.getCreateName());
        user.setCreateId(company.getCreateId());
        user.setPhone(company.getPhone());
        user.setRoleId(roleId);
        user.setName(company.getName()+"管理员");
        user.setUsername(company.getName()+"管理员");
        return userService.addUser(user);
    }

    /**
     * 通过公司菜单创建角色菜单对象
     * @param companyMenu 公司菜单
     * @return 角色菜单
     */
    private RoleMenu createRoleMenuByCompanyMenu(CompanyMenu companyMenu){
        Integer adminRoleId=userService.getAdminRoleIdByCompanyId(companyMenu.getCompanyId());
        RoleMenu roleMenu=new RoleMenu();
        roleMenu.setRoleId(adminRoleId);
        roleMenu.setMenuId(companyMenu.getMenuId());
        return roleMenu;
    }

    /**
     * 获取公司所有角色
     * @param companyId 公司id
     * @return 角色集合
     */
    private List<Role> getCompanyRoles(Integer companyId){
       return roleService.getCompanyRoles(companyId);
    };

    @Override
    public   Role getCompanyAdminRole(Integer companyId){
        return roleService.getCompanyAdminRole(companyId);
    }



}
