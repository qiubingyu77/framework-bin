package com.bin.framework.redis.ops.impl;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @autor qiubingyu
 * @ClassName AbstrackRedisOps
 * @date 2020/12/18
 **/
abstract class AbstrackRedisOps<T> implements FactoryBean {

    protected Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    @Autowired
    protected RedisTemplate redisTemplate;

    @Override
    public Class<T> getObjectType() {
        return (Class<T>) type;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
