package com.wqddg.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.support.collections.DefaultRedisList;

import java.util.List;

/**
 * @Author: wqddg
 * @ClassName BeanConfig
 * @DateTime: 2021/12/8 21:41
 * @remarks : #
 */
@Configuration
public class BeanConfig {
    @Autowired
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }
}
