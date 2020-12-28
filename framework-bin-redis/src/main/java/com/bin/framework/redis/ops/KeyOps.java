package com.bin.framework.redis.ops;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @autor qiubingyu
 * @ClassName KeyOps
 * @date 2020/12/23
 **/
public interface KeyOps {
    /**
     * @param key        键
     * @param timeToLive 过期时间值
     * @param timeUnit   过期时间单位
     * @return
     */
    boolean expire(String key, long timeToLive, TimeUnit timeUnit);

    /**
     * 是否过期
     *
     * @param timeToLive
     * @return
     */
    boolean expireAt(String key, long timeToLive);

    /**
     * 是否过期
     *
     * @param date
     * @return
     */
    boolean expireAt(String key, Date date);

    /**
     * @return
     */
    boolean clearExpire(String key);

    /**
     * @return
     */
    long remainTimeToLive(String key);

    boolean delete(String key);

    boolean unlink(String key);

    void rename(String key, String newName);

    boolean renamenx(String key, String newName);

    boolean isExists(String key);
}
