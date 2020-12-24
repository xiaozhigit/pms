package com.xxx.pms.controller;


import com.xxx.pms.response.Response;
import com.xxx.pms.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Api(tags={"认证管理"})
@RestController()
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthService authService;


    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username", value = "用户名", dataType="string", required=true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", required = true)
    })
    @PostMapping("login")
    public Response login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username,password);
    }


    @ApiOperation(value = "刷新token", notes = "刷新token")
    @ApiImplicitParam(value="鉴权token", name="Authorization", paramType="header", dataType="string", required=true)
    @PostMapping("refreshToken")
    public Response refreshToken(HttpServletRequest request){
        return authService.refreshToken(request);
    }


   /* @PostMapping("getUserInfo")
    @PreAuthorize("hasAuthority ('ROLE_admin')")
    public String getUserInfo(){

        String currentUser = "";
        Object principl = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principl instanceof UserDetails) {
            return  ((UserDetails)principl).getAuthorities().toString();
        }else {
            currentUser = principl.toString();
        }
        return " some product info,currentUser is: "+currentUser+SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }*/

}
