package com.xxx.pms.mapper;

import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskUserMapper extends Mapper<TaskUser> {

    @Select("select * from sys_task_user where user_id=#{userId}")
    List<TaskUser> selectByUserId(Integer userId);
    @Select("select * from sys_task_user where task_id in(select id from sys_task  where project_id =#{projectId})")
    List<TaskUser> selectByProject(Integer projectId);
    @Select("select user_id from sys_task_user where task_id in (select id from sys_task where project_id =#{projectId})")
    List<Integer> getProjectTaskUserId(Integer projectId);
}
