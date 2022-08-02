package com.wqddg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: wqddg
 * @ClassName SwaggerConfig
 * @DateTime: 2021/11/13 17:54
 * @remarks : # 本类是关于接口开发文档的一个类   添加接口文档
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * swagger会帮我们生成接口文档
     * 1.配置生成的文档信息
     * 2.配置生成规则
     */
    /*Docket配置接口文档信息*/
    @Bean
    public Docket getDocket(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("锋迷商城")
                .description("黑马老师的锋迷商城项目")
                .version("V1.0.0")
                .contact(new Contact("wqddg","www.wqddg.com","3102544231@qq.con"));
        ApiInfo apiInfo=apiInfoBuilder.build();
        Docket docket=new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo)//指定生成的文档的封面信息:文档标题、版本、作者
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wqddg.controller"))//需要写出api文档的类
//                .paths(PathSelectors.regex("/user/"))//对前缀为user的生成
                .paths(PathSelectors.any())//对所有的
                .build();
        ;

        return docket;
    }


}
