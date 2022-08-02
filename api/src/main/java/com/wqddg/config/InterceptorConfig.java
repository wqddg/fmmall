package com.wqddg.config;

import com.wqddg.interceptor.CheckTokenIterceptor;
import com.wqddg.interceptor.SetTimtInterception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: wqddg
 * @ClassName InterceptorConfig
 * @DateTime: 2021/11/16 14:29
 * @remarks : # 拦截器加载到系统中
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private CheckTokenIterceptor checkTokenIterceptor;
    @Autowired
    private SetTimtInterception interception;
    /**
     * 拦截所有的接口 除了带user的接口
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkTokenIterceptor)
                .addPathPatterns("/shopcart/**")
                .addPathPatterns("/useraddr/**")
                .addPathPatterns("/order/**")
                .addPathPatterns("/user/check")
                .excludePathPatterns("/user/**")
                .excludePathPatterns("/pay/**")
                .excludePathPatterns("/product/**")
                .excludePathPatterns("/index/**");
        registry.addInterceptor(interception)
                .addPathPatterns("/**");
    }
}
