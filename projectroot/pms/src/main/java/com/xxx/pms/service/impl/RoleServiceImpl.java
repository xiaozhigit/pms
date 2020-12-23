package com.xxx.pms.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.pms.entity.Role;
import com.xxx.pms.mapper.RoleMapper;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.service.RoleService;
import com.xxx.pms.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserService userService;

    @Override
    public int add(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    @Transactional
    public int deleteById(Integer id) {
        Role role=selectById(id);
        roleMapper.deleteByPrimaryKey(id);
        return userService.
                updateUserRoleIdByCompanyIdAndRoleId(role.getCompanyId(),role.getId());
    }

    @Override
    public int updateById(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public Role selectById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Role> selectByCondition(String name, String description) {
        Example example = new Example(Role.class);
        example.selectProperties("name", name);
        example.selectProperties("description", description);
        return roleMapper.selectByExample(example);
    }

    @Override
    public PageInfo<Role> selectByPage(RequestParamPage<Role> parameter) {
        PageHelper.startPage(parameter.getPage(),parameter.getPageSize());
        return new PageInfo<>(roleMapper.select(parameter.getParam()));
    }
}