package com.bin.framework.redis.locks;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @autor qiubingyu
 * @ClassName DistributedLock
 * @date 2020/12/24
 **/
@Component
 class DistributedLock implements DistLock {

    @Autowired
    RedissonClient redissonClient;

    @Override
    public void lock(String lock) {
        redissonClient.getLock(lock);
    }

    @Override
    public boolean tryLock(String lock) {
        return redissonClient.getLock(lock).tryLock();
    }

    @Override
    public boolean tryLock(String lock, long time, TimeUnit unit) throws InterruptedException {
        return redissonClient.getLock(lock).tryLock(time,unit);
    }

    @Override
    public void unlock(String lock) {
        redissonClient.getLock(lock).unlock();
    }

    @Override
    public Condition newCondition(String lock) {
        return redissonClient.getLock(lock).newCondition();
    }
}
