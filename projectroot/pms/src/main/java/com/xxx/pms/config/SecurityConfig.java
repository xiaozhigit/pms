package com.xxx.pms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("myUserDetailsService")
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Resource
    private UnauthorizedHandler unauthorizedHandler;

    @Resource
    private RestAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // 自定义token配置
        http
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()// 权限不足走这里,被GlobalExceptionHandler拦截 所以这里没有必要配置了
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()// 认证失败走这里
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//  //禁用session 这里才可以禁用security的session机制。
                .and()
                .authorizeRequests()
                // 配置无需认证的资源
                .antMatchers( "/doc.html").permitAll()//"/sign", "/error/**","/swagger-resources","/v2/api-docs",
                .antMatchers("/auth/login","/swaggerTest").permitAll()///auth/login
                .antMatchers("/static/**").permitAll()
                .antMatchers("/websocket/**").permitAll()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN") //admin开头的请求，需要admin权限
//                .antMatchers("/article/**").hasRole("USER") //需登陆才能访问的url
//                .antMatchers("/logout").authenticated()
                .anyRequest().authenticated()  //默认其它的请求都需要认证，这里一定要添加
                .and()
                .csrf().disable()  //CRSF禁用，因为不使用session
//                .sessionManagement().disable()//  放在这里 session的方式没有被禁用 要放在外面
                .formLogin().disable() //禁用form登录
                .cors()  //支持跨域  spring security会从CorsConfigurationSource中取跨域配置，所以我们需要定义一个Bean
                .and()   //添加header设置，支持跨域和ajax请求
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList( //对于返回给浏览器的Response的Header也需要添加跨域配置：
                new Header("Access-control-Allow-Origin","*"),
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and() //拦截OPTIONS请求，直接返回header
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                //添加登录filter
//                .apply(new JsonLoginConfigurer<>()).loginSuccessHandler(jsonLoginSuccessHandler())
//                .and()
//                //添加token的filter
//                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler()).permissiveRequestUrls("/logout")
//                .and()
                //使用默认的logoutFilter
                .logout().disable();
//                .logoutUrl("/logout")  //默认就是"/logout"
//                .addLogoutHandler(tokenClearLogoutHandler)  //logout时清除token
//                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());//logout成功后返回200

    }

    /**
     * 配置不需要认证访问的静态的文件
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/fonts/**","/js/**","/img/**", "/webjars/**", "**/favicon.ico", "/note.txt");
        web.ignoring().antMatchers(
                "swagger-ui.html",
                "**/swagger-ui.html",
                "doc.html",
                "**/doc.html",
                "/favicon.ico",
                "/**/*.css",
                "/**/*.js",
                "/**/*.png",
                "/**/*.gif",
                "/swagger-resources/**",
                "/v2/**",
                "/**/*.ttf"
        );
    }

    /**
     *配置获取用户信息的渠道和验证密码的加密方式
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在这里指定认证用户的来源之后yml配置中的账号就会失效
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("zhangsan")
//				.password(passwordEncoder().encode("123456")).roles("ADMIN");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
