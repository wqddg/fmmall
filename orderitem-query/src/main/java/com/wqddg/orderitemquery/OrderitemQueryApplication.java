package com.wqddg.orderitemquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.wqddg.orderitemquery.dao")
@EnableEurekaClient
public class OrderitemQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderitemQueryApplication.class, args);
    }

}
