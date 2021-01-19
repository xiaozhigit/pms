package com.xxx.pms.controller;

import com.xxx.pms.entity.User;
import com.xxx.pms.po.RequestParamPage;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.AuthService;
import com.xxx.pms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;


/***
 * @Author: chenjun
 * @date: 2020-12-23
 *
 * @desc: 用户管理
 */
@Api(tags={"用户管理"})
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    AuthService authService;
    @Resource
    UserService userService;

    @ApiOperation(value = "增加用户", notes = "增加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "user" , value = "创建用户表单" , dataType = "User")
    })
    @PostMapping("addUser")
    public Response addUser(@RequestBody User user){
        return userService.insertUser(user);
    }


    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "form" , value = "修改后的用户表单数据" , dataType = "User")
    })
    @PostMapping("updateUser")
    public Response updateUser(@RequestBody User form){
        return userService.updateUser(form);
    }


    @ApiOperation(value = "根据id查询用户", notes = "根据id查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "用户id" , dataType = "int")
    })
    @PostMapping("getUserById")
    public Response getUserById(int id){
        return userService.getUserById(id);
    }


    @ApiOperation(value = "根据id删除用户", notes = "根据id删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "用户id" , dataType = "int")
    })
    @PostMapping("deleteUserById")
    public Response deleteUserById(int id){
        return userService.deleteUserById(id);
    }

    @ApiOperation(value = "根据id禁用,启用用户", notes = "根据id禁用,启用用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "id" , value = "用户id" , dataType = "int"),
            @ApiImplicitParam(name = "statue" , value = "true:正常,false:禁用" , dataType = "Boolean")
    })
    @PostMapping("updateUserStatueById")
    public Response updateUserStatueById(int id, Boolean statue){
        return userService.updateUserStatueById(id,statue);
    }


    @ApiOperation(value = "分页条件查询用户列表", notes = "分页条件查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "companyId" , value = "公司id" , dataType = "int", required=true)
    })
    @PostMapping("getUserListByPage")
    public Response getUserListByPage(@RequestBody RequestParamPage<User> form, int companyId){
        return userService.getUserListByPage(form, companyId);
    }


    @ApiOperation(value = "下拉列表选择用户", notes = "下拉列表选择用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "companyId" , value = "公司id" , dataType = "int", required=true)
    })
    @PostMapping("getUserList")
    public Response getUserList(int companyId){
        return userService.getUserList(companyId);
    }


    /**
     * 修改密码
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true),
            @ApiImplicitParam(name = "oldPassword" , value = "旧密码" , dataType = "string"),
            @ApiImplicitParam(name = "newPassword" , value = "新密码" , dataType = "string"),
            @ApiImplicitParam(name = "userId" , value = "用户id" , dataType = "int", required=true)
    })
    @PostMapping("updatePassword")
    public Response updatePassword(String oldPassword, String newPassword, int userId) {
        return userService.updatePassword(oldPassword,newPassword,userId);
    }

}
