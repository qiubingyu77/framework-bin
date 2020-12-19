package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.SetOps;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName SetOps
 * @date 2020/12/18
 **/
class SetOpsFactoryBean extends AbstrackRedisOps<SetOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForSet(),a));
    }
}
