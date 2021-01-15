package com.xxx.pms.controller;

import com.xxx.pms.entity.Project;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
