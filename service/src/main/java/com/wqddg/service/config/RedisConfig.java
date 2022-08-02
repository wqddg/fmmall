package com.wqddg.service.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: wqddg
 * @ClassName RedisConfig
 * @DateTime: 2021/12/9 21:10
 * @remarks : #
 */

@Configuration
public class RedisConfig {
    @Value("${redisson.addr.singleAddr.host}")
    private String host;

    private String password;
    @Value("${redisson.addr.singleAddr.database}")
    private int database;

    @Bean
    public RedissonClient redissonClient(){
        Config config=new Config();
        config.useSingleServer().setAddress(host).setDatabase(database);
        return Redisson.create(config);
    }
}
