package com.xxx.pms.config;

import javax.servlet.http.HttpServletResponse;


import com.xxx.pms.constant.AccessStateCodeConstant;
import com.xxx.pms.response.Response;
import com.xxx.pms.util.ResponseUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 为了处理404的请求自定义返回值
    /*@ExceptionHandler(NoHandlerFoundException.class)
    public Response globalNullPointerException(HttpServletResponse response,
                                               NoHandlerFoundException exception) {
        return Response.fillResponseInfo(AccessStateCodeConstant.NOT_FOUNT);
    }*/

    @ExceptionHandler(AccessDeniedException.class)
    public Response AccessDeniedException(HttpServletResponse response, Exception e){
        return ResponseUtils.fillState(AccessStateCodeConstant.ACCESS_DENIED);
    }

    /**
     * 密码不正确异常处理
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(BadCredentialsException.class)
    public Response BadCredentialsException(HttpServletResponse response, Exception e){
//        log.error("异常信息如下",e);
        return ResponseUtils.fillState(AccessStateCodeConstant.PASSWORD_ERROR);
    }

    /**
     * 用户名不存在异常处理
     * @param response
     * @param e
     * @return
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Response InternalAuthenticationServiceException(HttpServletResponse response, Exception e){
//        log.error("异常信息如下",e);
        return ResponseUtils.fillState(AccessStateCodeConstant.USERNAME_NOT_FOUND_CODE);
    }

    /**
     * @Description:统一异常处理
     * @param response
     * @param e
     * @return
     * @author: yuan.liang
     * @email: liangyuan@dreamixtech.com
     * @date: 2019年12月23日 下午3:44:16
     */
    @ExceptionHandler(Exception.class)
    public Response globalException(HttpServletResponse response, Exception e) {
        log.error("异常信息如下",e);
        return ResponseUtils.error();
    }

}
