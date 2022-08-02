package com.wqddg.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wqddg
 * @ClassName CorsConfig
 * @DateTime: 2021/11/28 23:18
 * @remarks : #
 */

@Configuration
public class CorsConfig   {
    /**
     * 配置跨域
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        //1.添加CORS配置消息
        CorsConfiguration config = new CorsConfiguration();
        //允许的域  不要写* 否则cookie就无法使用
        List<String> lists=new ArrayList<>();
        lists.add("http://localhost:63343");
        //是否发送cookie消息
        config.setAllowedOrigins(lists);
        config.setAllowCredentials(true);
        //允许的请求方式
        config.addAllowedMethod("*");
        //允许的头信息
        config.addAllowedHeader("*");
        //有效时长
        config.setMaxAge(3600L);
        //添加映射路径  我们拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
