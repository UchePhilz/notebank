/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.notebank.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 *
 * @author Uche Powers
 */
@Configuration
public class SwaggerConfig {
    
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Notebank App")
            .description("This documentation contains APIs to the Notebank API")
            .license(null)
            .licenseUrl(null)
            .termsOfServiceUrl(null)
            .version("1.0.0")
            //.contact(new Contact("Uche Powers", "uchephilz@gmail.com"))
            .build();
    }
    
    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .build()
            .apiInfo(apiInfo());
    }
    
}
