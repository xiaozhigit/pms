package com.xxx.pms.mapper;

import com.xxx.pms.entity.User;
import java.util.Map;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {
    // 这种继承Mapper的方式 可以不用mapper.xml文件

    public List<Map<String, Object>> getAllUserBySelf();

    @Select("select * from sys_user where 1=1")
    public List<Map<String, Object>> getAllUserBySelfSql();
    
    
    @Update("update sys_user set role_id=null where company_id=#{companyId} and role_id=#{roleId}")
    int updateUserRoleIdByCompanyIdAndRoleId(@Param("companyId") Integer companyId,@Param("roleId") Integer roleId);


    @Select("select count(*) from sys_user where phone=#{phoneNumber} or username=#{phoneNumber}")
    int selectByPhoneNumber(String phoneNumber);

    /**
     * 通过公司id查询管理员角色id
     * @param companyId 公司id
     * @return 管理员角色id
     */
    @Select("select role_id from sys_user where company_id=#{companyId} and id=(select admin_id from sys_company where id=#{companyId})")
    Integer getAdminRoleIdByCompanyId(Integer companyId);


    User selectUserById(int id);

    List<User> selectUserList(User user);

    @Select("select count(*) from sys_user where username=#{phoneNumber} and id!=#{userId}")
    int selectPhoneIsRepeat(@Param("userId") Integer userId, @Param("phoneNumber") String phoneNumber);
}
