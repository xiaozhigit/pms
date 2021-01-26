package com.xxx.pms.controller;

import com.xxx.pms.entity.Iterative;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.IterativeService;
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
 * @date: 2021-1-17
 *
 * @desc: 迭代管理
 */
@Api(tags={"迭代管理"})
@RestController
@RequestMapping("Iterative")
public class IterativeController {

    @Resource
    IterativeService IterativeService;

    @ApiOperation(value = "增加迭代", notes = "增加迭代")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "创建迭代表单" , dataType = "Iterative")
    })
    @PostMapping("addIterative")
    public Response addIterative(@RequestBody Iterative form){
        return IterativeService.addIterative(form);
    }


    @ApiOperation(value = "修改迭代", notes = "修改迭代")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "修改后的迭代表单数据" , dataType = "Iterative")
    })
    @PostMapping("updateIterative")
    public Response updateIterative(@RequestBody Iterative form){
        return IterativeService.updateIterative(form);
    }


    @ApiOperation(value = "根据id查询迭代", notes = "根据id查询迭代")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "迭代id" , dataType = "int")
    })
    @PostMapping("getIterativeById")
    public Response getIterativeById(int id){
        return IterativeService.getIterativeById(id);
    }


    @ApiOperation(value = "根据id删除迭代", notes = "根据id删除迭代")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "迭代id" , dataType = "int")
    })
    @PostMapping("deleteIterativeById")
    public Response deleteIterativeById(int id){
        return IterativeService.deleteIterativeById(id);
    }


    @ApiOperation(value = "分页条件查询迭代列表", notes = "分页条件查询迭代列表")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("getIterativeListByPage")
    public Response getIterativeListByPage(@RequestBody RequestParamPage<Iterative> form){
        return IterativeService.getIterativeListByPage(form);
    }



    @ApiOperation(value = "根据项目id查询出未完成的最新迭代的一条数据", notes = "根据项目id查询出未完成的最新迭代的一条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "projectId" , value = "项目id" , dataType = "int")
    })
    @PostMapping("getIterativeByProjectId")
    public Response getIterativeByProjectId(int projectId){
        return IterativeService.getIterativeByProjectId(projectId);
    }

}
