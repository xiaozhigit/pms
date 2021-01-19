package com.xxx.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.*;
import com.xxx.pms.mapper.CompanyMapper;
import com.xxx.pms.mapper.CompanyMenuMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.CompanyService;
import com.xxx.pms.service.RoleMenuService;
import com.xxx.pms.service.RoleService;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.PinYinUtils;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xxx.pms.constant.AccessStateCodeConstant.*;

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
    public Response add(Company company) {
        if (companyNameIsExist(company.getName())) {
            return ResponseUtils.fillState(COMPANY_REPEAT);
        }
        if (userService.phoneIsExist(company.getPhone())) {
            return ResponseUtils.fillState(PHONE_NUMBER_REPEAT);
        }
        //设置公司状态为正常
        company.setStatue(true);
        int result = companyMapper.insertSelective(company);
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
                return ResponseUtils.success();
            }
            return ResponseUtils.fillState(FAIL);
        }
        return ResponseUtils.fillState(FAIL);
    }

    @Override
    public Response deleteById(Integer id) {
        //设置公司状态为禁用
        Company company=new Company();
        company.setId(id);
        company.setStatue(false);
        int result = companyMapper.updateByPrimaryKeySelective(company);
        return result > 0 ? ResponseUtils.success() : ResponseUtils.error();
    }

    @Override
    public Response updateById(Company company) {
        String companyName=company.getName();
        if (!StringUtils.isEmpty(companyName)&&companyNameIsRepeat(company)) {
            return ResponseUtils.fillState(COMPANY_REPEAT);
        }
        if (!StringUtils.isEmpty(company.getPhone()) || !StringUtils.isEmpty(company.getAdminName())) {
            Company company1=selectById(company.getId());
            if (userService.phoneIsRepeat(company1.getAdminId(),company.getPhone())) {
                return ResponseUtils.fillState(PHONE_NUMBER_REPEAT);
            }
            company1.setPhone(company.getPhone());
            company1.setAdminName(company.getAdminName());
            updateCompanyAdminInfo(company1);
        }
        //公司编辑管理员不变
        company.setAdminId(null);
        int result = companyMapper.updateByPrimaryKeySelective(company);
        return result > 0 ? ResponseUtils.success() : ResponseUtils.error();
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
        role.setDescription(company.getName()+"的管理员");
        role.setName("管理员");
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
        user.setName(company.getAdminName());
        user.setUsername(company.getPhone());
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

    /**
     * 判断公司名称是否重复
     * @param company 公司实体
     * @return 结果
     */
    public Boolean companyNameIsRepeat(Company company){
        return companyMapper.companyNameIsRepeat(company)>0;
    }

    /**
     * 判断公司名称是否存在
     * @param companyName 公司名称
     * @return 结果
     */
    private Boolean companyNameIsExist(String companyName){
        return getCompanyListByName(companyName).size()>0;
    }

    /**
     * 根据公司名称查询公司
     * @param companyName 公司名称
     * @return 公司集合
     */
    public List<Company> getCompanyListByName(String companyName){
        Company company=new Company();
        company.setName(companyName.trim());
        company.setStatue(true);
      return companyMapper.select(company);
    }

    /**
     * 更新公司管理员信息
     * @param company 公司
     */
    private void updateCompanyAdminInfo(Company company) {
        User user = new User();
        user.setId(company.getAdminId());
        if (!StringUtils.isEmpty(company.getPhone())) {
            user.setUsername(company.getPhone());
            user.setPhone(company.getPhone());
        }
        if (!StringUtils.isEmpty(company.getAdminName())) {
            user.setName(company.getAdminName());
            String pinYinHeadChar = PinYinUtils.getPinYinHeadChar(user.getName());
            user.setInitials(pinYinHeadChar);
        }
        userService.updateByPrimaryKeySelective(user);
    }



}
