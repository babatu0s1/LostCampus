package com.example.finalexam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Spring MVC 配置类。
 * 用于注册自定义拦截器。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final StudentTokenInterceptor studentTokenInterceptor;

    public WebConfig(StudentTokenInterceptor studentTokenInterceptor) {
        this.studentTokenInterceptor = studentTokenInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(studentTokenInterceptor)
                .addPathPatterns("/api/**");
    }
}
