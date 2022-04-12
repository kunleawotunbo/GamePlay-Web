/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunleawotunbo.gameplay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Olakunle Awotunbo
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
      
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kunleawotunbo.tunbor.controller"))
                //.paths(PathSelectors.ant("/test/*"))
                .paths(PathSelectors.any())
                .build();
    }
  
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
            .title("GamePlay")
            .description("This is a GamePlay App")
            .version("0.0.1")
            .termsOfServiceUrl("http://terms-of-services.url")
            .license("Apache 2.0")
            .licenseUrl("http://url-to-license.com")
            .build();
    }
    
    
}