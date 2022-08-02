package com.wqddg.prodcctskuquery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wqddg.prodcctskuquery.dao")
public class ProdcctSkuQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdcctSkuQueryApplication.class, args);
    }

}
