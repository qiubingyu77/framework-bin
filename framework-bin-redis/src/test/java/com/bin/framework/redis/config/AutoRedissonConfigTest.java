package com.bin.framework.redis.config;

import com.bin.framework.redis.BaseTest;
import com.bin.framework.redis.ops.ValueOps;
import junit.framework.TestCase;
import org.junit.Test;
import org.redisson.client.RedisClient;

/**
 * @author qiubingyu
 * @ClassName AutoRedissonConfigTest.java
 * @createTime 2020/12/24
 **/
public class AutoRedissonConfigTest extends BaseTest {

    @Test
    public void testRedissonClient() {
        ValueOps<String> ops = applicationContext.getBean(ValueOps.class);
        ops.set("qby", "qby");
    }
}