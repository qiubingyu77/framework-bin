package com.bin.framework.redis.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @autor qiubingyu
 * @ClassName DistLock
 * @date 2020/12/24
 **/
public interface DistLock {

     void lock(String lock);

     boolean tryLock(String lock);

     boolean tryLock(String lock,long time, TimeUnit unit) throws InterruptedException;

     void unlock(String lock);

     Condition newCondition(String lock);
}
