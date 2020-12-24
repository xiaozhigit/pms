package com.xxx.pms.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xxx.pms.entity.User;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.mapper.UserMapper;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.ResponseUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    public Page<User> findUserListByPage(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        return (Page<User>) userMapper.selectAll();
    }

    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return userMapper.selectOne(user);
    }


    /**
     * 增加用户
     * @param user
     * @return
     */
    @Override
    public Response addUser(User user) {
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
    public Response updateUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return ResponseUtils.success();
    }

    /**
     * 查询用户
     * @param id
     * @return
     */
    @Override
    public User getUserById(Integer id) {
       return userMapper.selectByPrimaryKey(id);
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
    public Response getUserListByPage(RequestParamPage<User> form) {
        PageHelper.startPage(form.getPage(), form.getPageSize());
        List<User> userList = userMapper.select(form.getParam());
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return ResponseUtils.successData(pageInfo);
    }

    @Override
    public List<User> selectUsersByCompanyIdAndRoleId(Integer companyId, Integer roleId) {
        Example example=new Example(User.class);
        example.and().andEqualTo("companyId",companyId).andEqualTo("roleId",roleId);
        return userMapper.selectByExample(example);
    }

    @Override
    public int updateUserRoleIdByCompanyIdAndRoleId(Integer companyId, Integer roleId) {
//        Example example =Example.builder(User.class).select("companyId",companyId).andEqualTo("roleId",roleId).build();
//        Example.builder(User.class).where(s)
//        Example example = new Example(User.class);
//        Example.Criteria criteria=example.createCriteria().andEqualTo("roleId",roleId);
//        user.setRoleId(null);
        return userMapper.updateUserRoleIdByCompanyIdAndRoleId(companyId,roleId);
    }

    @Override
    public int updateUserRoleIdByRoleId(Integer roleId) {
        return userMapper.updateUserRoleIdByRoleId(roleId);
    }
}
