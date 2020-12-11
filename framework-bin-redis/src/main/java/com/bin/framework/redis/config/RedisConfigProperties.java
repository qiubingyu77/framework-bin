package com.bin.framework.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qiubingyu
 * @ClassName RedisConfigProperties.java
 * @createTime 2020/12/11
 **/
@Data
@ConfigurationProperties(prefix = "framework.redis")
public class RedisConfigProperties {
    /**
     * 开启redis模块功能
     */
    private boolean enable;
    /**
     * 机器节点 配置模板 127.0.0.1:6379,127.0.0.1:6380
     */
    private String host;
    /**
     * 超时时间
     */
    private int timeout;
    /**
     * 数据库0-15
     */
    private int dbIndex;
}
