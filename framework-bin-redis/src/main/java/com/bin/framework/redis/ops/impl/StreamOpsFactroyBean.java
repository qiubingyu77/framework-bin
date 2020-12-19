package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.StreamOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName StreamOps
 * @date 2020/12/18
 **/
@Component
class StreamOpsFactroyBean extends AbstrackRedisOps<StreamOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForStream(),a));
    }
}
