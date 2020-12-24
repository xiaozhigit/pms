package com.xxx.pms.mapper;

import java.util.List;
import java.util.Map;


import com.xxx.pms.entity.User;
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

}
