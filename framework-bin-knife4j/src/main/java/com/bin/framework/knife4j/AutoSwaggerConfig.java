package com.bin.framework.knife4j;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan
@Configuration
@EnableConfigurationProperties(value = {Knife4jProperties.class})
class AutoSwaggerConfig {
}
