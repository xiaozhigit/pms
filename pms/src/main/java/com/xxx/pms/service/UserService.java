package com.xxx.pms.service;


import com.github.pagehelper.Page;
import com.xxx.pms.entity.SysUser;
import com.xxx.pms.entity.vo.RequestParamPage;
import com.xxx.pms.response.Response;

public interface UserService {

    /**
     * 获取用户列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<SysUser> findUserListByPage(Integer pageNo, Integer pageSize);

    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    SysUser findUserByUsername(String username);


    /**
     * 增加用户
     * @param user
     * @return
     */
    Response addUser(SysUser user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Response updateUser(SysUser user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    Response getUserById(String id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Response deleteUserById(String id);

    /**
     * 分页条件查询用户列表
     * @param form
     * @return
     */
    Response getUserListByPage(RequestParamPage<SysUser> form);


}
