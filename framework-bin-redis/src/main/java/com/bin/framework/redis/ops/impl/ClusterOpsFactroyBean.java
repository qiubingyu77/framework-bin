package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.ClusterOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * @autor qiubingyu
 * @ClassName ClusterOps
 * @date 2020/12/18
 **/
@Component
class ClusterOpsFactroyBean extends AbstrackRedisOps<ClusterOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForCluster(),a));
    }
}
