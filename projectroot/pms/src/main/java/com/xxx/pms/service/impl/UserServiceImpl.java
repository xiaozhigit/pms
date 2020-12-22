package com.xxx.pms.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xxx.pms.entity.SysUser;
import com.xxx.pms.entity.vo.RequestParamPage;
import com.xxx.pms.mapper.UserMapper;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper userMapper;

    /**
     * 获取用户列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<SysUser> findUserListByPage(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        return (Page<SysUser>) userMapper.selectAll();
    }

    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    @Override
    public SysUser findUserByUsername(String username){
        SysUser user = new SysUser();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }


    /**
     * 增加用户
     * @param user
     * @return
     */
    @Override
    public Response addUser(SysUser user) {
        //随机生成id
        user.setId("sss");
        //对密码加密
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userMapper.insert(user);
        return ResponseUtils.success();
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @Override
    public Response updateUser(SysUser user) {
        userMapper.updateByPrimaryKeySelective(user);
        return ResponseUtils.success();
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public Response getUserById(String id) {
        SysUser user = userMapper.selectByPrimaryKey(id);
        return ResponseUtils.successData(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @Override
    public Response deleteUserById(String id) {
        userMapper.deleteByPrimaryKey(id);
        return ResponseUtils.success();
    }

    /**
     * 分页条件查询用户列表
     * @param form
     * @return
     */
    @Override
    public Response getUserListByPage(RequestParamPage<SysUser> form) {
        PageHelper.startPage(form.getPage(), form.getPageSize());
        List<SysUser> userList = userMapper.select(form.getParam());
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        return ResponseUtils.successData(pageInfo);
    }
}
