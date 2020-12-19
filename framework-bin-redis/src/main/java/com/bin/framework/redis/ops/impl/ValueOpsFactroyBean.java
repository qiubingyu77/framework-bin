package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.ValueOps;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;


/**
 * @autor qiubingyu
 * @ClassName ValueOpsFactroyBean
 * @date 2020/12/18
 **/
@Component
public
class ValueOpsFactroyBean extends AbstrackRedisOps<ValueOps> {
    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(classLoader,new Class[]{getObjectType()},(p, h, a)-> h.invoke(redisTemplate.opsForValue(),a));
    }
}
