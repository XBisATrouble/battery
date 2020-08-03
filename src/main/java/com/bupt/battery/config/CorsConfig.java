package com.bupt.battery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

// 解决跨域问题
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 配置允许跨域访问的路径
                .allowedOrigins("*") // 判断 origin 是否合法  Header中是否包含 Origin。如果包含则说明为 CORS请求
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") // 判断 method 是否合法
                .maxAge(3600)
                .allowCredentials(true);
    }
}