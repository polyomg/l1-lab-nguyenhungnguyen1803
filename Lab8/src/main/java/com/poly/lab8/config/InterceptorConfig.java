package com.poly.lab8.config;

import com.poly.lab8.interceptor.AuthInterceptor;
import com.poly.lab8.interceptor.LogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    @Autowired
    LogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 1️⃣ Interceptor kiểm tra đăng nhập & quyền admin
        registry.addInterceptor(authInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**"
                )
                .excludePathPatterns("/admin/home/index");

        // 2️⃣ Interceptor ghi log truy cập (chạy sau AuthInterceptor)
        registry.addInterceptor(logInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/account/change-password",
                        "/account/edit-profile",
                        "/order/**"
                )
                .excludePathPatterns("/admin/home/index");
    }
}
