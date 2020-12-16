package com.bin.framework.redis.config;

import com.bin.framework.redis.dto.RedisStrategy;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qiubingyu
 * @ClassName RedisConfigProperties.java
 * @createTime 2020/12/11
 **/
@Data
@ConfigurationProperties(prefix = "framework.redis")
class RedisConfigProperties {
    /**
     * 开启redis模块功能
     */
    private boolean enable;
    /**
     * 连接超时时间
     */
    private int connectionTimeout;
    /**
     * 读取超时时间
     */
    private int soTimeout;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 最大连接数
     */
    private int maxTotal;
    /**
     * 最大空闲连接数
     */
    private int maxIdle;
    /**
     * 最小连接数
     */
    private int minIdle;

    /**
     * 模式
     * 单机 Standalone
     * 哨兵 Sentinel
     * 集群 Cluster
     */
    private RedisStrategy strategy;
}
