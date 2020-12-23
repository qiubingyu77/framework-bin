package com.bin.framework.redis.config;

/**
 * @autor qiubingyu
 * @ClassName RedisMode
 * @date 2020/12/24
 **/
public enum RedisMode {
    SINGLE, //单机模式
    SENTINEL, //哨兵模式
    CLUSTER; //集群模式
}
