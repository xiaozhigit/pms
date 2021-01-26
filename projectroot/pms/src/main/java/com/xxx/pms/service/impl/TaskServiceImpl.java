package com.xxx.pms.service.impl;

import com.google.gson.Gson;
import com.xxx.pms.constant.MsgType;
import com.xxx.pms.entity.Task;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.mapper.TaskMapper;
import com.xxx.pms.mapper.TaskUserMapper;
import com.xxx.pms.response.ProjectProgressTask;
import com.xxx.pms.response.Message;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.ProjectService;
import com.xxx.pms.service.TaskService;
import com.xxx.pms.service.UserService;
import com.xxx.pms.util.ResponseUtils;
import com.xxx.pms.util.WebSocketUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xxx.pms.constant.AccessStateCodeConstant.TASK_PROGRESS;
import static com.xxx.pms.constant.AccessStateCodeConstant.TASK_START_ERROR;

@Service
public class TaskServiceImpl implements TaskService {
    @Resource
    private TaskUserMapper taskUserMapper;
    @Resource
    private ProjectService projectService;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private UserService userService;

    @Override
    public Response quickAddTask(Task task) {
        // 检查是否有正在开始的任务
        if (userIsProgress(task.getCreateUserId())) {
            return ResponseUtils.fillState(TASK_PROGRESS);
        }
        // 创建任务
        taskMapper.insertSelective(task);
        // 创建任务的责任人，快速创建为创建人本人
        TaskUser taskUser = new TaskUser();
        taskUser.setTaskId(task.getId());
        taskUser.setUserId(task.getCreateUserId());
        taskUser.setUserName(task.getCreateUserName());
        taskUser.setUserType("zr");
        taskUser.setState(0);
        taskUser.setActualSecond(0);
        taskUserMapper.insertSelective(taskUser);
        // 推送消息
        sendNoticeToProjectUser(buildMsg(MsgType.TASK_START,task),task.getProjectId());
       return ResponseUtils.success();
    }

    @Override
    public List<ProjectProgressTask> getProjectUserProgressTask(Integer projectId) {
        return taskMapper.getProjectUserProgressTask(projectId);
    }

    @Override
    public Response userStartTask(Integer userTaskId) {
        TaskUser taskUser2 = selectById(userTaskId);
        Task task=getTaskById(taskUser2.getTaskId());
        if (userIsProgress(taskUser2.getUserId())) {
            return ResponseUtils.fillState(TASK_START_ERROR);
        }
        TaskUser taskUser = new TaskUser();
        taskUser.setId(userTaskId);
        taskUser.setGmtStart(new Date());
        taskUser.setState(0);
        taskUserMapper.updateByPrimaryKeySelective(taskUser);
        //发送通知消息
        sendNoticeToProjectUser(buildMsg(MsgType.TASK_START,task),task.getProjectId());
        return ResponseUtils.success();
    }

    @Override
    public void userSuspendTask(Integer userTaskId) {
        TaskUser taskUser, updateTaskUser;
        taskUser = taskUserMapper.selectByPrimaryKey(userTaskId);
        updateTaskUser = new TaskUser();
        updateTaskUser.setId(userTaskId);
        long useTime = System.currentTimeMillis() - taskUser.getGmtStart().getTime();
        updateTaskUser.setActualSecond((int) (taskUser.getActualSecond() + (useTime / 1000)));
        updateTaskUser.setState(-1);
        taskUserMapper.updateByPrimaryKeySelective(updateTaskUser);
        //发送通知消息
        sendNoticeToProjectUser(buildMsg(MsgType.TASK_SUSPEND,taskUser),getTaskById(taskUser.getTaskId()).getProjectId());
    }

    @Override
    public void userFinishTask(Integer userTaskId) {
        TaskUser taskUser = taskUserMapper.selectByPrimaryKey(userTaskId);
        // 计算完成任务的时间
        long useTime = System.currentTimeMillis() - taskUser.getGmtStart().getTime();
        taskUser.setActualSecond((int) (taskUser.getActualSecond() + useTime / 1000));
        taskUser.setState(1);
        taskUser.setGmtFinish(new Date());
        taskUserMapper.updateByPrimaryKeySelective(taskUser);
        finishTask(taskUser.getTaskId());
        //发送通知消息
        sendNoticeToProjectUser(buildMsg(MsgType.TASK_FINISH,taskUser),getTaskById(taskUser.getTaskId()).getProjectId());
    }

    @Override
    public TaskUser getUserProgressTaskByUserId(Integer userId) {
        return taskUserMapper.getUserProgressTaskByUserId(userId);
    }

    @Override
    public List<TaskUser> getUserIncompleteTask(Integer userId) {
        return taskUserMapper.getUserIncompleteTask(userId);
    }

    @Override
    public TaskUser selectById(Integer id) {
        return taskUserMapper.selectByPrimaryKey(id);
    }


    /**
     * 通过任务id获取任务
     * @param taskId 任务id
     * @return 任务实体类
     */
    private Task getTaskById(Integer taskId) {
        return taskMapper.selectByPrimaryKey(taskId);
    }



    /**
     * 判断分配该任务的所有用户是否已完成，完成则更新该任务状态为完成
     *
     * @param taskId 任务id
     */
    private void finishTask(Integer taskId) {
        int count = taskUserMapper.selectTaskZRUserList(taskId);
        if(count >0) return;
        Task task = new Task();
        task.setId(taskId);
        task.setGmtFinish(new Date());
        task.setState(1);
        taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 判断用户是否有在处理的任务
     *
     * @param userId 用户id
     * @return 是否又在处理的任务，true 有 false 没有
     */
    private Boolean userIsProgress(Integer userId) {
        return getUserProgressTaskByUserId(userId) != null;
    }

    /**
     * 构建任务开始时发送消息对象
     * @param task task实体类
     * @return 消息对象
     */
    private Message<Map<String, Object>> buildMsg(MsgType msgType,Task task){
        Message<Map<String,Object>> message = new Message<Map<String,Object>>();
        message.setMsgType(msgType);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",task.getCreateUserId());
        map.put("taskId",task.getId());
        map.put("taskContext",task.getContext());
        map.put("projectId",task.getProjectId());
        message.setData(map);
        return message;
    }

    private Message<Map<String, Object>> buildMsg(MsgType msgType ,TaskUser taskUser){
        Message<Map<String,Object>> message = new Message<Map<String,Object>>();
        message.setMsgType(msgType);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userId",taskUser.getUserId());
        map.put("taskId",taskUser.getTaskId());
        message.setData(map);
        return message;
    }

    /**
     * 发送消息
     * @param message 消息实体类
     * @param projectId 项目ID
     */
    private void sendNoticeToProjectUser(Message message,int projectId){
        List<Integer> userIds = projectService.getUserIdListByProjectId(projectId);
        WebSocketUtils.sendMsgToUserList(userIds,new Gson().toJson(message));
    }


}
