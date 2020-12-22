package com.bin.framework.redis.config;

import com.bin.framework.redis.ops.*;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;

/**
 * @autor qiubingyu
 * @ClassName RedisOpsFactory
 * @date 2020/12/21
 **/
@Configuration
@ConditionalOnBean(RedisTemplate.class)
class RedisOpsFactory {

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public <K,V> ValueOps<K,V> valueOps(){
        return (ValueOps<K, V>) ProxyFactory.getProxy(ValueOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForValue(),methodInvocation.getArguments()));
    }

    @Bean
    public <K,V> ListOps<K, V> listOps(){
        return (ListOps<K, V>) ProxyFactory.getProxy(ListOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForList(),methodInvocation.getArguments()));
    }

    @Bean
    public <K,V> SetOps<K,V> setOps(){
        return (SetOps<K, V>) ProxyFactory.getProxy(SetOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForSet(),methodInvocation.getArguments()));
    }

    @Bean
    public <H, HK, HV> HashOps<H, HK, HV> hashOps(){
        return (HashOps<H, HK, HV>) ProxyFactory.getProxy(HashOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForHash(),methodInvocation.getArguments()));
    }

    @Bean
    public <K, HK, HV> StreamOps<K, HK, HV> streamOps(){
        return (StreamOps<K, HK, HV>) ProxyFactory.getProxy(StreamOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForStream(),methodInvocation.getArguments()));
    }

    @Bean
    public <K,V> ZSetOps<K,V> zSetOps(){
        return (ZSetOps<K,V>) ProxyFactory.getProxy(ZSetOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForZSet(),methodInvocation.getArguments()));
    }

    @Bean
    public <K,V> GeoOps<K,V> geoOps(){
        return (GeoOps<K,V>) ProxyFactory.getProxy(GeoOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForGeo(),methodInvocation.getArguments()));
    }

    @Bean
    public <K,V> HyperLogOps<K,V> hyperLogOps(){
        return (HyperLogOps<K,V>) ProxyFactory.getProxy(HyperLogOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForHyperLogLog(),methodInvocation.getArguments()));
    }

    @Bean
    public <K,V> ClusterOps<K,V> clusterOps(){
        return (ClusterOps<K,V>) ProxyFactory.getProxy(ClusterOps.class,(MethodInterceptor) methodInvocation -> methodInvocation.getMethod().invoke(redisTemplate.opsForCluster(),methodInvocation.getArguments()));
    }


}
