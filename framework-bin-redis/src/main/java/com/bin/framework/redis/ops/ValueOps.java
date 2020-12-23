package com.bin.framework.redis.ops;


import java.util.concurrent.TimeUnit;

/**
 * @autor qiubingyu
 * @ClassName ValueOps
 * @date 2020/12/17
 **/
public interface ValueOps<V>{

    long size(String key);

    V get(String key);

    V getAndDelete(String key);

    boolean trySet(String key,V value);

    boolean trySet(String key,V value, long timeToLive, TimeUnit timeUnit);

    boolean compareAndSet(String key,V newVal, V oldVal);

    V getAndSet(String key,V value);

    void set(String key,V value);

    void set(String key,V value, long timeToLive, TimeUnit timeUnit);

}
