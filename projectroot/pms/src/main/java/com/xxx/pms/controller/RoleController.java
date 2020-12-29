package com.xxx.pms.controller;

import com.github.pagehelper.PageInfo;
import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.entity.Menu;
import com.xxx.pms.entity.Role;
import com.xxx.pms.entity.RoleMenu;
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
import java.util.List;


@Api(tags={"角色管理"})
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes="新增角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="role",value="角色实体类",dataType="Role",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("addRole")
    public Response addRole(@RequestBody Role role){
        int  result= roleService.add(role);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "通过角色id删除角色", notes="删除角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="角色实体类id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("deleteRoleById")
    public Response deleteRoleById(Integer id){
        int  result= roleService.deleteById(id);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "通过角色id更新角色", notes="更新角色接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="role",value="角色实体类",dataType="Role",required=true)
    })
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="String", required=true)
    @PostMapping("updateRoleById")
    public Response updateRoleById(@RequestBody Role role){
        int  result= roleService.updateById(role);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "分页条件查询角色", notes = "分页条件查询角色接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getRoleByPage")
    public Response getRoleByPage(@RequestBody  RequestParamPage<Role> paramPage) {
        PageInfo<Role> result = roleService.selectByPage(paramPage);
        return ResponseUtils.successData(result);
    }


    @ApiOperation(value = "角色添加菜单", notes = "角色添加菜单接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="roleId",value="角色Id",dataType="Integer",required=true)
    })
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("roleAddMenu")
    public Response roleAddMenu(Integer roleId,@RequestBody List<Integer> menuIds) {
        int result = roleService.roleAddMenus(roleId,menuIds);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "角色删除菜单", notes = "角色删除菜单接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("roleDeleteMenu")
    public Response roleDeleteMenu(@RequestBody RoleMenu roleMenu) {
        int result = roleService.roleDeleteMenu(roleMenu);
        return result>0? ResponseUtils.success():ResponseUtils.error();
    }

    @ApiOperation(value = "获取角色菜单", notes = "获取角色菜单接口")
    @ApiImplicitParam(value = "鉴权token", name = "Authorization", paramType = "header", dataType = "String", required = true)
    @PostMapping("getRoleMenu")
    public Response getRoleMenu(String roleId) {
        List<Menu> result = roleService.getRoleMenu(roleId);
        return ResponseUtils.successData(result);
    }

}
