package com.xxx.pms.service;

import com.xxx.pms.entity.Task;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.response.ProjectProgressTask;
import com.xxx.pms.response.Response;

import java.util.List;

public interface TaskService {

    /**
     * 快速添加并开始用户任务
     * @param task
     * @return
     */
    Response quickAddTask(Task task);

    /**
     * 获取项目用户正在执行的任务
     * @param projectId 项目id
     * @return 用户集合
     */
    List<ProjectProgressTask> getProjectUserProgressTask(Integer projectId);

    /**
     * 开始用户任务
     * @param userTaskId 用户任务id
     * @return 结果
     */
    Response userStartTask(Integer userTaskId);

    /**
     * 暂停用户任务
     * @param taskId 任务id
     * @return 执行结果
     */
    void userSuspendTask(Integer taskId);

    /**
     * 完成用户任务
     * @param taskId 用户任务id
     * @return
     */
    void userFinishTask(Integer taskId);

    /**
     * 根据用户ID查询用户正在进行中的任务
     * @param userId
     * @return
     */
    TaskUser getUserProgressTaskByUserId(Integer userId);

    /**
     * 根据用户ID查询查询用户所有的任务
     * @param userId
     * @return
     */
    List<TaskUser> getUserIncompleteTask(Integer userId);

    /**
     *
     * @param id
     * @return
     */
    TaskUser selectById(Integer id);



}
