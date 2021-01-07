package com.xxx.pms.config;

import com.google.gson.Gson;

import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 权限不足的时候这里处理
 * 加入了globalExceptionHandler 被拦截了 没有走这里
 */
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = new Gson().toJson(ResponseUtils.fillState(AccessStateCodeConstant.ACCESS_DENIED));
        printWriter.write(body);
        printWriter.flush();
    }
}
