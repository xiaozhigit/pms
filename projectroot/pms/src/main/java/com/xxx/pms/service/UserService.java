package com.xxx.pms.service;

import com.xxx.pms.entity.User;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import java.util.List;
import java.util.Map;


public interface UserService {
    /**
     * 增加用户
     * @param user
     * @return
     */
    Response insertUser(User user);

    /**
     * 新增用户
     * @param user 用户对象
     * @return 新增结果
     */
    Map addUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    Response updateUser(User user);

    /**
     * 更新用户
     * @param user 用户实体
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 查询用户
     * @param id
     * @return
     */
    Response getUserById(int id);

    User  getUserById(Integer userId);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Response deleteUserById(int id);

    /**
     * 分页条件查询用户列表
     * @param form
     * @param companyId
     * @return
     */
    Response getUserListByPage(RequestParamPage<User> form, int companyId);

    /**
     * 通过公司id和角色id查询用户
     * @param companyId 公司id
     * @param roleId  角色id
     * @return 符合条件的用户集合
     */
    List<User> selectUsersByCompanyIdAndRoleId(Integer companyId,Integer roleId);

    Response updatePassword(String oldPassword, String newPassword, int userId);

    Response updateUserStatueById(int id, Boolean statue);
    /**
     * 更新用户角色id通过公司id和角色id
     * @param companyId 公司id
     * @param roleId 角色id
     * @return 更新结果
     */
    int updateUserRoleIdByCompanyIdAndRoleId(Integer companyId, Integer roleId);
    /**
     * 查询用户手机号是否存在系统中
     * @param phoneNumber 手机号
     * @return 查询结果
     */
    Boolean phoneIsExist(String phoneNumber);

    /**
     * 查询用户手机号是否重复
     * @param userId 用户id
     * @param phoneNumber 手机号
     * @return 查询结果
     */
    Boolean phoneIsRepeat(Integer userId,String phoneNumber);

    /**
     * 通过公司id查询公司管理员的角色id
     * @return 公司管理员角色id
     */
    Integer getAdminRoleIdByCompanyId(Integer companyId);

    Response getUserList(int companyId);


    /**
     * 根据项目id查询用户任务
     * @param projectId 项目id
     * @return 用户集合
     */
    List<User> getProjectTaskUser(Integer projectId);

    /**
     * 根据公司id获取用户id集合
     * @param companyId 公司id
     */
    List<Integer> getUserIdListByCompanyId(Integer companyId);
}
