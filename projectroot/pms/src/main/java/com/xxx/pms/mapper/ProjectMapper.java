package com.xxx.pms.mapper;

import com.xxx.pms.entity.Project;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;

@Repository
public interface ProjectMapper extends Mapper<Project> {

    @Select("select * from project where id in (select project_id from project_user where user_id=#{userId})")
    List<Project> selectProjectByUserId(Integer userId);

    @Select("select * from project where id =(select project_id from sys_task where id= #{taskId})")
    Project getProjectByTaskId(Integer taskId);
    // 这种继承Mapper的方式 可以不用mapper.xml文件
}
