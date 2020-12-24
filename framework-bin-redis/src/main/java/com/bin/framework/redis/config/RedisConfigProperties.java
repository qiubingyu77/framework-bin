package com.bin.framework.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

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
     * 最大连接数
     */
    private int maxTotal;
    /**
     * 最小空闲连接数
     */
    private int minIdle;
    /**
     * 数据库 集群模式下无效
     */
    private int db;

    /**
     * 模式
     * 单机 Standalone
     * 哨兵 Sentinel
     * 集群 Cluster
     */
    private RedisMode redisMode;
    /**
     * 主节点
     */
    private String master;
    /**
     * redis 从节点 单机无效
     */
    private List<String> redisNodes;
}
