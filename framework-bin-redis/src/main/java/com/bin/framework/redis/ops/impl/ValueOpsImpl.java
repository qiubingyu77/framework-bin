package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.ValueOps;
import org.redisson.api.RBucket;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


/**
 * @autor qiubingyu
 * @ClassName ValueOpsImpl
 * @date 2020/12/23
 **/
@Component
class ValueOpsImpl<V> extends AbstrackRedisOps implements ValueOps<V> {

    @Override
    public long size(String key) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.size();
    }

    @Override
    public V get(String key) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    @Override
    public V getAndDelete(String key) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.getAndDelete();
    }

    @Override
    public boolean trySet(String key, V value) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.trySet(value);
    }

    @Override
    public boolean trySet(String key, V value, long timeToLive, TimeUnit timeUnit) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.trySet(value,timeToLive,timeUnit);
    }

    @Override
    public boolean compareAndSet(String key, V oldVal, V newVal) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.compareAndSet(oldVal,newVal);
    }

    @Override
    public V getAndSet(String key, V value) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        return bucket.getAndSet(value);
    }

    @Override
    public void set(String key, V value) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    @Override
    public void set(String key, V value, long timeToLive, TimeUnit timeUnit) {
        final RBucket<V> bucket = redissonClient.getBucket(key);
        bucket.set(value,timeToLive,timeUnit);
    }
}
