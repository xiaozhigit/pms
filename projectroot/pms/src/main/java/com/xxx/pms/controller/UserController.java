package com.xxx.pms.controller;


import com.xxx.pms.entity.SysUser;
import com.xxx.pms.entity.vo.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.AuthService;
import com.xxx.pms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/***
 * @Author: chenjun
 * @date: 2020-10-9
 *
 * @desc: 用户管理
 */
@Api(tags={"用户管理"})
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username", value = "用户名", dataType="string", required=true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true)
    })
    @PostMapping("login")
    public Response login(String username, String password){
        return authService.login(username,password);
    }


    @ApiOperation(value = "增加用户", notes = "增加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "创建用户表单" , dataType = "SysUser")
    })
    @PostMapping("addUser")
    public Response addUser(@RequestBody SysUser form){
        return userService.addUser(form);
    }


    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "修改后的用户表单数据" , dataType = "SysUser")
    })
    @PostMapping("updateUser")
    public Response updateUser(@RequestBody SysUser form){
        return userService.updateUser(form);
    }


    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "用户id" , dataType = "String")
    })
    @GetMapping("getUserById")
    public Response getUserById(String id){
        return userService.getUserById(id);
    }


    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "用户id" , dataType = "String")
    })
    @GetMapping("deleteUserById")
    public Response deleteUserById(String id){
        return userService.deleteUserById(id);
    }


    @ApiOperation(value = "分页条件查询用户列表", notes = "分页条件查询用户列表")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("getUserListByPage")
    public Response getUserListByPage(@RequestBody RequestParamPage<SysUser> form){
        return userService.getUserListByPage(form);
    }


}
