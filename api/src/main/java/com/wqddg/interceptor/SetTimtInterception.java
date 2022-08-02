package com.wqddg.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wqddg
 * @ClassName SetTimtInterception
 * @DateTime: 2021/12/8 22:02
 * @remarks : #
 */
@Component
public class SetTimtInterception implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate template;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token!=null){
            String tem = template.boundValueOps(token).get();
            if (tem!=null){
                template.boundValueOps(token).expire(30, TimeUnit.MINUTES);
            }
        }
        return true;
    }
}
