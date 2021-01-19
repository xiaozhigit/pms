package com.xxx.pms.service.impl;

import com.xxx.pms.constant.MsgType;
import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.mapper.ProjectMapper;
import com.xxx.pms.mapper.TaskMapper;
import com.xxx.pms.mapper.TaskUserMapper;
import com.xxx.pms.po.Message;
import com.xxx.pms.service.TaskService;
import com.xxx.pms.service.WebSocketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskUserMapper taskUserMapper;

    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private WebSocketService webSocketService;


    @Override
    public int add(TaskUser taskUser) {
        return taskUserMapper.insert(taskUser);
    }

    @Override
    public int deleteById(Integer id) {
        return taskUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(TaskUser taskUser) {
        taskUserMapper.updateByPrimaryKey(taskUser);
        return sendNoticeToProjectUser(taskUser);
    }

    @Override
    public TaskUser selectById(Integer id) {
        return taskUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TaskUser> selectUserTaskByUserId(Integer userId) {
        return taskUserMapper.selectByUserId(userId);
    }



    //通过项目任务id查询项目信息
    private Project getProjectByTaskId(Integer taskId) {
        return projectMapper.getProjectByTaskId(taskId);
    }


    /**
     * 通过项目id获取所有参与该项目的所有用户id
     *
     * @param projectId 项目id
     * @return 项目参与用户id
     */
    private List<Integer> getProjectTaskUserId(Integer projectId) {
        return taskUserMapper.getProjectTaskUserId(projectId);
    };

    /**
     * 项目中任务状态变更是通知项目参与用户
     * @param taskUser 项目任务实体类
     * @return 发送结果
     */
    private int sendNoticeToProjectUser(TaskUser taskUser) {
        Project project = getProjectByTaskId(taskUser.getTaskId());
        List<Integer> userIds = getProjectTaskUserId(project.getId());
        Message message1 = new Message();
        message1.setMsgType(MsgType.TASK_STATUS_CHANGE);
        message1.setProjectId(project.getId());
        message1.setTaskId(taskUser.getTaskId());
        message1.setUserId(taskUser.getUserId());
        message1.setTasStatus(taskUser.getState());
        message1.setContent("项目:" + project.getName() + "中" + taskUser.getUserName() + "任务状体改变");
        return webSocketService.sendMessagePatch(userIds, message1.toString());
    }

}
