package com.xxx.pms.service;

import com.xxx.pms.entity.Company;
import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.TaskUser;

import java.util.List;
import java.util.Map;

public interface TaskService {

    int add(TaskUser taskUser);

    int deleteById(Integer id);

    int updateById(TaskUser taskUser);

    TaskUser selectById(Integer id);

    List<TaskUser> selectUserTaskByUserId(Integer userId);

    /**
     * 通过用户id获取用户参与的项目
     * @param userId 用户id
     * @return 用户参与的项目集合
     */
    List<Project> getUserJoinProjects(Integer userId);

    /**
     * 获取项目下参与人的任务状态
     * @param projectId 项目id
     * @return 任务结合
     */
    List<TaskUser> getProjectTaskUser(Integer projectId);
}
