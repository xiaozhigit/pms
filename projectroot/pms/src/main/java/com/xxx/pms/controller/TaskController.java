package com.xxx.pms.controller;

import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.TaskService;
import com.xxx.pms.service.WebSocketService;
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
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @Resource
    private WebSocketService webSocketService;

    @ApiOperation(value = "新增用户任务", notes="新增用户任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskUser",value="用户任务实体类",dataType="TaskUser",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("addUserTask")
    public Response addTask(@RequestBody TaskUser taskUser){
        int  result= taskService.add(taskUser);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "通过用户任务id删除用户任务", notes="删除用户任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户任务实体类id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("deleteUserTaskById")
    public Response deleteUserTaskById(Integer id){
        int  result= taskService.deleteById(id);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "通过用户任务id更新用户任务", notes="更新用户任务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Task",value="用户任务实体类",dataType="Task",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("updateUserTaskById")
    public Response updateTaskById(@RequestBody TaskUser taskUser){
        int  result= taskService.updateById(taskUser);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }



    @ApiOperation(value = "通过用户id获取用户任务", notes = "通过用户id获取用户任务接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getUserTaskByUserId")
    public Response getTaskMenu(Integer userId) {
        List<TaskUser> result = taskService.selectUserTaskByUserId(userId);
        return ResponseUtils.successData(result);
    }

    @ApiOperation(value = "通过用户id查询用户参与的项目", notes = "通过用户id获取参与的项目接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getUserJoinProjects")
    public Response getUserJoinProjects(Integer userId) {
        List<Project> result = taskService.getUserJoinProjects(userId);
        return ResponseUtils.successData(result);
    }


    @ApiOperation(value = "获取项目下所有参与人的任务", notes = "通过项目下所有参与人的任务接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("projectTaskUser")
    public Response projectTaskUser(Integer projectId) {
        List<TaskUser> result = taskService.getProjectTaskUser(projectId);
        return ResponseUtils.successData(result);
    }

}
