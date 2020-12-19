package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.GeoOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName GeoOps
 * @date 2020/12/18
 **/
@Component
class GeoOpsFactroyBean extends AbstrackRedisOps<GeoOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForGeo(),a));
    }
}
