package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.HashOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName HashOps
 * @date 2020/12/18
 **/
@Component
class HashOpsFactroyBean extends AbstrackRedisOps<HashOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForHash(),a));
    }
}
