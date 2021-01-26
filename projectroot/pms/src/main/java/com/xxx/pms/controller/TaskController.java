package com.xxx.pms.controller;

import com.xxx.pms.entity.Task;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.response.ProjectProgressTask;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.TaskService;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@Api(tags={"任务管理"})
@RestController
@RequestMapping("task")
public class TaskController {
    @Resource
    private TaskService taskService;


    @ApiOperation(value = "快速添加并开始任务", notes="快速添加并开始用户任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="task",value="任务实体类",dataType="Task",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("addUserTask")
    public Response quickAddTask(@RequestBody Task task){
        return taskService.quickAddTask(task);
    }

    @ApiOperation(value = "获取项目下所有参与人的任务", notes = "通过项目下所有参与人的任务接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getProjectUserProgressTask")
    public Response getProjectUserProgressTask(Integer projectId) {
        List<ProjectProgressTask> result = taskService.getProjectUserProgressTask(projectId);
        return ResponseUtils.successData(result);
    }

    @ApiOperation(value = "开始用户任务", notes="开始用户任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userTaskId",value="用户任务id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("startUserTask")
    public Response userStartTask(Integer userTaskId){
        return  taskService.userStartTask(userTaskId);
    }

    @ApiOperation(value = "暂停用户任务", notes="暂停任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userTaskId",value="用户任务id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("suspendUserTask")
    public Response userSuspendTask(Integer userTaskId){
        taskService.userSuspendTask(userTaskId);
        return ResponseUtils.success();
    }

    @ApiOperation(value = "完成用户任务", notes="完成任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userTaskId",value="用户任务id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("finishUserTask")
    public Response userFinishTask(Integer userTaskId){
        taskService.userFinishTask(userTaskId);
        return ResponseUtils.success();
    }

    @ApiOperation(value = "通过用户id查询用户正在做的任务", notes = "通过用户id查询用户正在做的任务接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getUserProgressTaskByUserId")
    public Response getUserProgressTaskByUserId(Integer userId) {
        TaskUser result = taskService.getUserProgressTaskByUserId(userId);
        return ResponseUtils.successData(result);
    }


    @ApiOperation(value = "通过用户id获取用户所有未完成的任务", notes = "通过用户id获取用户未完成所有任务接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getUserIncompleteTask")
    public Response getUserIncompleteTask(Integer userId) {
        List<TaskUser> result = taskService.getUserIncompleteTask(userId);
        return ResponseUtils.successData(result);
    }

}
