package com.bin.framework.redis.dto;

import lombok.Data;

/**
 * 选择连接的方式
 * 模式
 * 单机 Standalone
 * 哨兵 Sentinel
 * 集群 Cluster
 */
@Data
public abstract class RedisStrategy {

    public RedisStrategy() {
    }

    public RedisStrategy(String name) {
        this.name = name;
    }

    /**
     * 模式
     * 单机 Standalone
     * 哨兵 Sentinel
     * 集群 Cluster
     */
    protected String name;

    /**
     * 数据库 单机 哨兵 才有当前属性
     */
    private int db;

    public static RedisStrategy defualtRedisStrategy = new StandaloneStrategy("Standalone");

}
