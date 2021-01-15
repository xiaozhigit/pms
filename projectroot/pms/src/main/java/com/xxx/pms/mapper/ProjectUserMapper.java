package com.xxx.pms.mapper;

import com.xxx.pms.entity.ProjectUser;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ProjectUserMapper extends Mapper<ProjectUser> {
    // 这种继承Mapper的方式 可以不用mapper.xml文件

}
