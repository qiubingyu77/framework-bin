package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.HyperLogOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName HyperLogOps
 * @date 2020/12/18
 **/
@Component
class HyperLogOpsFactroyBean extends AbstrackRedisOps<HyperLogOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForHyperLogLog(),a));
    }
}
