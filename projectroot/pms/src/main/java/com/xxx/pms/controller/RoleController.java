package com.xxx.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.entity.Role;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.RoleService;
import com.xxx.pms.util.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(tags={"角色管理"})
@RestController
@RequestMapping("/")
public class RoleController {
    @Resource
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes="新增角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="role",value="角色实体类",dataType="Role",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("role")
    public Response addRole(@RequestBody Role role){
        int  result= roleService.add(role);
        return result>0? ResponseUtils.error():ResponseUtils.error();
    }

    @ApiOperation(value = "通过角色id删除角色", notes="删除角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="角色实体类id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @DeleteMapping("role/{id}")
    public Response deleteRoleById(@PathVariable String id){
        int  result= roleService.deleteById(Integer.valueOf(id));
        return result>0? ResponseUtils.error():ResponseUtils.error();
    }

    @ApiOperation(value = "通过角色id更新角色", notes="更新角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="role",value="角色实体类",dataType="Role",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PatchMapping("role")
    public Response updateRoleById(@RequestBody Role role){
        int  result= roleService.updateById(role);
        return result>0? ResponseUtils.error():ResponseUtils.error();
    }

    @ApiOperation(value = "分页查询角色", notes="分页查询角色接口")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name="paramPage",value="分页查询实体类",dataType="RequestParamPage<Role>",required=true)
//    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @GetMapping("role")
    public Response getRoleByPage(@RequestBody RequestParamPage<Role> paramPage){
        PageInfo<Role>  result= roleService.selectByPage(paramPage);
        return  ResponseUtils.successData(result);
    }

}
