package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.ZSetOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName ZSetOps
 * @date 2020/12/18
 **/
@Component
class ZSetOpsFactroyBean extends AbstrackRedisOps<ZSetOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForZSet(),a));
    }
}
