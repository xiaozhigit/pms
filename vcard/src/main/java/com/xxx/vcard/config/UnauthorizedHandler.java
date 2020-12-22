package com.xxx.vcard.config;

import com.google.gson.Gson;

import com.xxx.vcard.constant.AccessStateCodeConstant;
import com.xxx.vcard.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * 认证失败这里处理
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //验证为未登陆状态会进入此方法,返回http状态码401 无权限
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = new Gson().toJson(ResponseUtils.fillState(AccessStateCodeConstant.AUTH_DENIED));
        printWriter.write(body);
        printWriter.flush();
    }
}
