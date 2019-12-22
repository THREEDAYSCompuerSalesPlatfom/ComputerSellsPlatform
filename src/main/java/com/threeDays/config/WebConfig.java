package com.threeDays.config;

import com.threeDays.Interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassNameWebConfig
 * @Date2019-12-2119:08 注册 拦截器
 **/
//@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截路径：必须登录才可以访问
        List<String> patterns = new ArrayList<>();
        patterns.add("/**");
        // 白名单：在黑名单范围内，却不需要登录就可以访问
        List<String> excludePatterns = new ArrayList<>();
        excludePatterns.add("/Login");
        // 注册拦截器
        registry .addInterceptor(new SessionInterceptor()).addPathPatterns(patterns) .excludePathPatterns(excludePatterns);
    }
}
