package com.bin.framework.redis.mq;

import com.google.common.collect.Sets;
import org.redisson.client.RedisClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.stereotype.Component;

/**
 * @autor qiubingyu
 * @ClassName RedisBeanDefinitionRegistrar
 * @date 2020/12/25
 **/
@Component
class RedisBeanDefinitionRegistrar implements BeanFactoryPostProcessor,InstantiationAwareBeanPostProcessor{

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        final Object bean = beanFactory.getBean(AnnotationConfigUtils.AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME);
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = (AutowiredAnnotationBeanPostProcessor) bean;
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationTypes(Sets.newHashSet(Autowired.class, Value.class, Producer.class));
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println(beanName);
        return null;
    }
}
