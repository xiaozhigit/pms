package com.xxx.pms.service.impl;



import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.entity.Company;
import com.xxx.pms.entity.User;
import com.xxx.pms.mapper.CompanyMapper;
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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
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
        User user = new User();
        user.setUsername(username);
        user = userMapper.selectOne(user);
        Boolean userStatue = user.getStatue();
        Company company = companyMapper.selectByPrimaryKey(user.getCompanyId());
        Boolean companyStatue = company.getStatue();
        //判断用户或公司是否被禁用
        if(userStatue && companyStatue){
            // 根据用户信息生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId",user.getId());
            claims.put("companyId",user.getCompanyId());
            claims.put("username",username);
            String token = JwtUtils.generateToken(user.getId()+"",claims);
            // 封装返回的数据  密码不返回
            user.setPassword("");
            Map<String,Object> data = new HashMap<>();
            data.put("token",token);
            data.put("user",user);
            data.put("company",company);
            return ResponseUtils.successData(data);
        }else {
            //用户或公司被禁用无法登录成功
            return ResponseUtils.fillState(new String[]{"200","用户或公司被禁用，无法登录"});
        }
    }

    /**
     * 刷新token的接口
     * @return
     * @param request
     */
    public Response refreshToken(HttpServletRequest request){
        String token = JwtUtils.getToken(request);
        Claims claims;
        try {
            claims = JwtUtils.checkToken(token);
        }catch (Exception e){
            return ResponseUtils.fillState(AccessStateCodeConstant.TOKEN_ERROR);
        }
        User user = userMapper.selectByPrimaryKey(claims.getSubject());
        claims.put("userId",user.getId());
        claims.put("companyId",user.getCompanyId());
        claims.put("userName",user.getUsername());
        Map<String,Object> data = new HashMap<>();
        String newToken = JwtUtils.generateToken(user.getId()+"",claims);
        data.put("newToken",newToken);
        return ResponseUtils.successData(data);
    }

}
