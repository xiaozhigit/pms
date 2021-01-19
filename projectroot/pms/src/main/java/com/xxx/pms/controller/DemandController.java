package com.xxx.pms.controller;

import com.xxx.pms.entity.Demand;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.DemandService;
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
 * @date: 2021-1-13
 *
 * @desc: 需求管理
 */
@Api(tags={"需求管理"})
@RestController
@RequestMapping("Demand")
public class DemandController {

    @Resource
    DemandService DemandService;

    @ApiOperation(value = "增加需求", notes = "增加需求")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "创建需求表单" , dataType = "Demand")
    })
    @PostMapping("addDemand")
    public Response addDemand(@RequestBody Demand form){
        return DemandService.addDemand(form);
    }


    @ApiOperation(value = "修改需求", notes = "修改需求")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "修改后的需求表单数据" , dataType = "Demand")
    })
    @PostMapping("updateDemand")
    public Response updateDemand(@RequestBody Demand form){
        return DemandService.updateDemand(form);
    }


    @ApiOperation(value = "根据id查询需求", notes = "根据id查询需求")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "需求id" , dataType = "int")
    })
    @PostMapping("getDemandById")
    public Response getDemandById(int id){
        return DemandService.getDemandById(id);
    }


    @ApiOperation(value = "根据id删除需求", notes = "根据id删除需求")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "需求id" , dataType = "int")
    })
    @PostMapping("deleteDemandById")
    public Response deleteDemandById(int id){
        return DemandService.deleteDemandById(id);
    }


    @ApiOperation(value = "分页条件查询需求列表", notes = "分页条件查询需求列表")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("getDemandListByPage")
    public Response getDemandListByPage(@RequestBody RequestParamPage<Demand> form){
        return DemandService.getDemandListByPage(form);
    }

    @ApiOperation(value = "保存需求列表排序", notes = "保存需求列表排序")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "page" , value = "当前页" , dataType = "int", required=true),
            @ApiImplicitParam(name = "pageSize" , value = "当前页数据条数" , dataType = "int", required=true)
    })
    @PostMapping("saveDemandList")
    public Response saveDemandList(@RequestBody List<Demand> demandList, int page, int pageSize){
        return DemandService.saveDemandList(demandList, page, pageSize);
    }

}
