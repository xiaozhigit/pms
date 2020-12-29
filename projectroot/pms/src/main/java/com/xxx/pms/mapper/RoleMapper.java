package com.xxx.pms.mapper;

import com.xxx.pms.entity.Role;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface RoleMapper extends Mapper<Role> {
    @Select("select * from sys_role where company_id=#{companyId}")
    List<Role> getCompanyRoles(Integer companyId);
}
