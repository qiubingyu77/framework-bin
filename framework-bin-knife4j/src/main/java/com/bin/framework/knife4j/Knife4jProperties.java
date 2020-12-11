package com.bin.framework.knife4j;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "framework.swagger")
public class Knife4jProperties  {
    /**
     * 是否启用对应模块
     */
    private boolean enable;
    /**
     * 扫描包
     */
    private String basePackage;

    private String title;
    private String description;
    private String version;
    private String termsOfServiceUrl;
}
