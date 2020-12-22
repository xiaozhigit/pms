package com.xxx.vcard.config;


import com.xxx.vcard.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证携带token的请求过滤器
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final RequestHeaderRequestMatcher requiresAuthenticationRequestMatcher;


    public JwtAuthenticationFilter(){
        //拦截header中带Authorization的请求
        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher("Authorization");
    }

    protected boolean requiresAuthentication(HttpServletRequest request,
                                             HttpServletResponse response) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    protected String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        token = token.substring(7,token.length());
        return token;
    }

    /**
     * 这里些校验token的规则，如果通过校验则把token放入security中
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 如果请求头中没有Authorization字段则直接通过
        if (!requiresAuthentication(request, response)) {
            filterChain.doFilter(request, response);
            return;
        }
        // 校验token逻辑
//        String ss ="Bearer ";
        String token = getToken(request);
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("token:   "+token);
        // 检查token  如果解析失败则交给security 处理
        Claims claims = null;
        try{
            claims = JwtUtils.checkToken(token);
        }catch (Exception e){
            filterChain.doFilter(request, response);
            return;
        }
        String userId = claims.getSubject();
        String username = claims.get("username")+"";
        //校验成功把token放入security中
        if(!StringUtils.isEmpty(userId)&& !StringUtils.isEmpty(username)) {
            // 根据用户名查询用户权限
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_admin"));
            // 全部成功，则给security管理
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                    null,  authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
