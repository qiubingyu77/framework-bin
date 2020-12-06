package com.bin.framework.knife4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan
@Configuration
@EnableConfigurationProperties(value = {Knife4jProperties.class})
class AutoSwaggerConfig {

    @Autowired
    Knife4jProperties knife4jProperties;

    @Bean
    public String test1(){
        System.out.println(knife4jProperties.isEnable());
        return "test1";
    }

}
