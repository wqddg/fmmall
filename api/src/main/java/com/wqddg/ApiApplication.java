package com.wqddg;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: wqddg
 * @ClassName com.wqddg.ApiApplication
 * @DateTime: 2021/11/12 20:44
 * @remarks : #
 */
@SpringBootApplication
@MapperScan("com.wqddg.dao")
@EnableScheduling
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class,args);
    }




}