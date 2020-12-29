package com.xxx.pms.config;

import com.xxx.pms.constant.YmlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用来查看  springboot  配置的静态路径有哪些，点击 ResourceProperties.class 查看
 */

@Configuration(proxyBeanMethods = false)
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    YmlConstant constant;
    @Value("${file.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(constant.getCompanyLogoAccessPath()).addResourceLocations(constant.getCompanyLogoLocationPath());
        registry.addResourceHandler(constant.getUserLogoAccessPath()).addResourceLocations(constant.getUserLogoLocationPath());
        registry.addResourceHandler("/static/**").addResourceLocations("file:"+path);
    }
}
