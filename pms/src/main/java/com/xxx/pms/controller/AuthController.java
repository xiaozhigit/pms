package com.xxx.pms.controller;


import com.xxx.pms.response.Response;
import com.xxx.pms.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("auth")
public class AuthController {


    @Autowired
    AuthService authService;



    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    /**
     * 自定义的登陆接口前后端分离
     */
    @PostMapping(value = "login")
    public Response login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username,password);
    }

    /**
     * 刷新token接口
     * @param token
     * @return
     */
    @PostMapping("refreshToken")
    public Response refreshToken(String token){
        return authService.refreshToken(token);
        }



    @PostMapping("getUserInfo")
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
    }

}
