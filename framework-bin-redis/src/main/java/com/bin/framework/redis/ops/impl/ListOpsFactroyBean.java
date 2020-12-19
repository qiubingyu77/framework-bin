package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.ListOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName ListOps
 * @date 2020/12/18
 **/
@Component
class ListOpsFactroyBean extends AbstrackRedisOps<ListOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForList(),a));
    }
}
