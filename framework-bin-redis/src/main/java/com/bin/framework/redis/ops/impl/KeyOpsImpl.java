package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.KeyOps;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @autor qiubingyu
 * @ClassName KeyOpsImpl
 * @date 2020/12/23
 **/
@Component
class KeyOpsImpl extends AbstrackRedisOps implements KeyOps {
    @Override
    public boolean expire(String key, long timeToLive, TimeUnit timeUnit) {
        return redissonClient.getBucket(key).expire(timeToLive, timeUnit);
    }

    @Override
    public boolean expireAt(String key, long timeToLive) {
        return redissonClient.getBucket(key).expireAt(timeToLive);
    }

    @Override
    public boolean expireAt(String key, Date date) {
        return redissonClient.getBucket(key).expireAt(date);
    }

    @Override
    public boolean clearExpire(String key) {
        return redissonClient.getBucket(key).clearExpire();
    }

    @Override
    public long remainTimeToLive(String key) {
        return redissonClient.getBucket(key).remainTimeToLive();
    }

    @Override
    public boolean delete(String key) {
        return redissonClient.getBucket(key).delete();
    }

    @Override
    public boolean unlink(String key) {
        return redissonClient.getBucket(key).unlink();
    }

    @Override
    public void rename(String key, String newName) {
        redissonClient.getBucket(key).rename(newName);
    }

    @Override
    public boolean renamenx(String key, String newName) {
        return redissonClient.getBucket(key).renamenx(newName);
    }

    @Override
    public boolean isExists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

}
