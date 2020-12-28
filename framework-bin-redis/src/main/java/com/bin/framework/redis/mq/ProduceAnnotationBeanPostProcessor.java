package com.bin.framework.redis.mq;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * @autor qiubingyu
 * @ClassName RedisBeanDefinitionRegistrar
 * @date 2020/12/25
 **/
@Component
class ProduceAnnotationBeanPostProcessor implements BeanPostProcessor{
    @Autowired
    RedissonClient redissonClient;

    Map<Type,Produce> produceMap = Maps.newConcurrentMap();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //遍历bean字段
        ReflectionUtils.doWithFields(bean.getClass(), field -> {
            Producer producer = field.getAnnotation(Producer.class);
            Class<?> type = field.getType();
            if (producer != null && type.isAssignableFrom(Produce.class)){ //注解不为空，并且属于Produce的类型
                //获取字段泛型类
                Type[] actualTypeArguments = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
                //判断是否写入泛型类
                if (ArrayUtils.isNotEmpty(actualTypeArguments)){
                    //获取泛型类
                    Type clazz = actualTypeArguments[0];
                    //通过泛型创建对应的代理类,并缓存，防止同类型对象创建冗余
                    Produce produce = produceMap.computeIfAbsent(clazz, c-> {
                                Produce proxy = ProxyFactory.getProxy(Produce.class,new ProduceMethodInterceptor(redissonClient, producer.topic()));
                        return proxy;
                    });
                    ReflectionUtils.makeAccessible(field);
                    field.set(bean, produce);
                }
            }
        });

        return bean;
    }

    @AllArgsConstructor
    static class ProduceMethodInterceptor implements MethodInterceptor{
        private RedissonClient redissonClient; //redissionClient
        private String toipc; //主题
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
            Object[] arguments = methodInvocation.getArguments();
            long publish = redissonClient.getTopic(toipc).publish(arguments[0]);
            return publish == 1;
        }
    }
}
