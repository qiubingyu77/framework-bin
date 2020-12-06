package com.bin.framework.es;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConditionalOnProperty(prefix = "com.bin.framework.es.repository",name = "enable",havingValue = "true")
@ConfigurationProperties
public class ESProperties {
    public static void main(String[] args) {
    }
}
