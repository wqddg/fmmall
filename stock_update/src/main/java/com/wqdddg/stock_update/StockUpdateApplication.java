package com.wqdddg.stock_update;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.wqdddg.stock_update.dao")
public class StockUpdateApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockUpdateApplication.class, args);
    }

}
