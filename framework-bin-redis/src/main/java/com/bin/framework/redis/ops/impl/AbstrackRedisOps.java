package com.bin.framework.redis.ops.impl;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @autor qiubingyu
 * @ClassName AbstrackRedisOps
 * @date 2020/12/23
 **/
public abstract class AbstrackRedisOps {

    @Autowired
    protected RedissonClient redissonClient;

}
