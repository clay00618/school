package com.zzti.school.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;



//拦截器配之类
@Configuration
public class    MyWebMvcConfigurationSupport implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(MyWebMvcConfigurationSupport.class);


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/","/static/**","/user","/modify_password","/modify","/stu_register","/register");
        logger.info("------------addInterceptors-------------");

    }

}
