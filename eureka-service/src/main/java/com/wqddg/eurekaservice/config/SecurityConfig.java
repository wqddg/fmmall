package com.wqddg.eurekaservice.config;

import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: wqddg
 * @ClassName SecurityConfig
 * @DateTime: 2021/12/18 16:08
 * @remarks : #
 */
@Configuration
@EnableEurekaServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //web工具
        http.csrf().disable();
        //设置当前服务器的所有请求都要使用spring security的认证
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
