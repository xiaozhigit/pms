package com.xxx.pms.controller;

import com.xxx.pms.entity.Project;
import com.xxx.pms.entity.TaskUser;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.ProjectService;
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

/***
 * @Author: chenjun
 * @date: 2021-1-12
 *
 * @desc: 项目管理
 */
@Api(tags={"项目管理"})
@RestController
@RequestMapping("project")
public class ProjectController {

    @Resource
    ProjectService projectService;

    @ApiOperation(value = "增加项目", notes = "增加项目")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "创建项目表单" , dataType = "Project")
    })
    @PostMapping("addProject")
    public Response addProject(@RequestBody Project form){
        return projectService.addProject(form);
    }


    @ApiOperation(value = "修改项目", notes = "修改项目")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "修改后的项目表单数据" , dataType = "Project")
    })
    @PostMapping("updateProject")
    public Response updateProject(@RequestBody Project form){
        return projectService.updateProject(form);
    }


    @ApiOperation(value = "根据id查询项目", notes = "根据id查询项目")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "项目id" , dataType = "int")
    })
    @PostMapping("getProjectById")
    public Response getProjectById(int id){
        return projectService.getProjectById(id);
    }


    @ApiOperation(value = "根据id删除项目", notes = "根据id删除项目")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "项目id" , dataType = "int")
    })
    @PostMapping("deleteProjectById")
    public Response deleteProjectById(int id){
        return projectService.deleteProjectById(id);
    }


    @ApiOperation(value = "分页条件查询项目列表", notes = "分页条件查询项目列表")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("getProjectListByPage")
    public Response getProjectListByPage(@RequestBody RequestParamPage<Project> form){
        return projectService.getProjectListByPage(form);
    }


    @ApiOperation(value = "通过用户id查询用户参与的项目", notes = "通过用户id获取参与的项目接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getUserJoinProjects")
    public Response getUserJoinProjects(Integer userId) {
        List<Project> result = projectService.getUserJoinProjects(userId);
        return ResponseUtils.successData(result);
    }


    @ApiOperation(value = "获取项目下所有参与人的任务", notes = "通过项目下所有参与人的任务接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("projectTaskUser")
    public Response projectTaskUser(Integer projectId) {
        List<TaskUser> result = projectService.getProjectTaskUser(projectId);
        return ResponseUtils.successData(result);
    }

    @ApiOperation(value = "下拉列表选择项目", notes = "下拉列表选择项目")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "companyId" , value = "公司id" , dataType = "Integer", required=true)
    })
    @PostMapping("getProjectList")
    public Response getProjectList(Integer companyId){
        return projectService.getProjectList(companyId);
    }

    @ApiOperation(value = "切换项目", notes = "切换项目")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "projectId" , value = "项目id" , dataType = "Integer", required=true),
            @ApiImplicitParam(name = "userId" , value = "用户id" , dataType = "Integer", required=true)
    })
    @PostMapping("changeProject")
    public Response changeProject(Integer userId,Integer projectId){
        return projectService.changeProject(userId,projectId);
    }
}
