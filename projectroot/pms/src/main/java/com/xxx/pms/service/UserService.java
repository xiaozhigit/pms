package com.xxx.pms.service;


import com.github.pagehelper.Page;
import com.xxx.pms.entity.User;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;

import java.util.List;

public interface UserService {

    /**
     * 获取用户列表
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<User> findUserListByPage(Integer pageNo, Integer pageSize);

    /**
     * 根据用户名称查询用户信息
     * @param username
     * @return
     */
    User findUserByUsername(String username);


    /**
     * 增加用户
     * @param user
     * @return
     */
    Response addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Response updateUser(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    User getUserById(Integer id);

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
    Response getUserListByPage(RequestParamPage<User> form);

    /**
     * 通过公司id和角色id查询用户
     * @param companyId 公司id
     * @param roleId  角色id
     * @return 符合条件的用户集合
     */
    List<User> selectUsersByCompanyIdAndRoleId(Integer companyId,Integer roleId);

    /**
     * 更新用户角色id通过公司id和角色id
     * @param companyId 公司id
     * @param roleId 角色id
     * @return 更新结果
     */
    int updateUserRoleIdByCompanyIdAndRoleId(Integer companyId, Integer roleId);
}
