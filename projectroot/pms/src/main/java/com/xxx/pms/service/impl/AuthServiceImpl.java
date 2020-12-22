package com.xxx.pms.service.impl;



import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.entity.SysUser;
import com.xxx.pms.mapper.UserMapper;
import com.xxx.pms.response.Response;
import com.xxx.pms.service.AuthService;
import com.xxx.pms.util.JwtUtils;
import com.xxx.pms.util.ResponseUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    @Override
    public Response login(String username, String password){
        // 1 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, password);
        // 2 认证
        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        // 3 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 根据用户名查询用户信息
        SysUser user = new SysUser();
        user.setUsername(username);
        user = userMapper.selectOne(user);
        // 根据用户信息生成token
        Map<String, Object> claims = new HashMap<>();
        claims.put("companyId",user.getCompanyId());
        claims.put("username",username);
        String token = JwtUtils.generateToken(user.getId(),claims);
        // 封装返回的数据  密码不返回
        user.setPassword("");
        Map<String,Object> data = new HashMap<>();
        data.put("token",token);
        data.put("user",user);
        data.put("company","");
        return ResponseUtils.successData(data);
    }

    /**
     * 刷新token的接口
     * @return
     */
    // 刷新token的接口
    public Response refreshToken(String token){
        Claims claims;
        try {
            claims = JwtUtils.checkToken(token);
        }catch (Exception e){
            return ResponseUtils.fillState(AccessStateCodeConstant.TOKEN_ERROR);
        }
        SysUser user = userMapper.selectByPrimaryKey(claims.getSubject());
        claims.put("companyId",user.getCompanyId());
        String newToken = JwtUtils.generateToken(user.getId(),claims);
        return ResponseUtils.successData(newToken);
    }

}
