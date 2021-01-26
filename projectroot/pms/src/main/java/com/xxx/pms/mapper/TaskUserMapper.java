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

    @Select("select tu.*,t.context from sys_task_user tu,sys_task t where tu.user_id=#{userId} and tu.state!=1 and tu.user_type='zr' and tu.task_id=t.id order by gmt_start desc")
    List<TaskUser> getUserIncompleteTask(Integer userId);

    /**
     * 通过用户id 获取用户正在进行的任务
     */
    @Select("select st.*,sk.context from sys_task_user st left join sys_task sk on sk.id=st.task_id where st.user_id=#{userId} and st.state=0")
    TaskUser getUserProgressTaskByUserId(Integer userId);

    /**
     * 查询任务没有完成任务的责任人数量
     */
    @Select("select count(id) from sys_task_user where task_id=#{taskId} and user_type='zr' and state!=1")
    int selectTaskZRUserList(Integer taskId);
}
