package com.xxx.pms.service;

import com.github.pagehelper.Page;
import com.xxx.pms.entity.User;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    /**
     * 增加用户
     * @param user
     * @param request
     * @return
     */
    Response addUser(User user, HttpServletRequest request);

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
    Response getUserById(int id);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Response deleteUserById(int id);

    /**
     * 分页条件查询用户列表
     * @param form
     * @param request
     * @return
     */
    Response getUserListByPage(RequestParamPage<User> form, HttpServletRequest request);

    /**
     * 通过公司id和角色id查询用户
     * @param companyId 公司id
     * @param roleId  角色id
     * @return 符合条件的用户集合
     */
    List<User> selectUsersByCompanyIdAndRoleId(Integer companyId,Integer roleId);

    Response updatePassword(String oldPassword, String newPassword, HttpServletRequest request);

    Response updateUserStatueById(int id, Boolean statue);
    /**
     * 更新用户角色id通过公司id和角色id
     * @param companyId 公司id
     * @param roleId 角色id
     * @return 更新结果
     */
    int updateUserRoleIdByCompanyIdAndRoleId(Integer companyId, Integer roleId);

    int  updateUserRoleIdByRoleId(Integer roleId);
}
