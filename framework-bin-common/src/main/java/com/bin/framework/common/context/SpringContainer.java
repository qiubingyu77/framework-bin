package com.bin.framework.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @autor qiubingyu
 * @ClassName SpringContainer
 * @date 2020/12/22
 **/
@Order(0)
@Configuration
public class SpringContainer implements ApplicationContextAware {

    static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContainer.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name){
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return applicationContext.getBean(name,clazz);
    }

    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotation){
        return applicationContext.getBeansWithAnnotation(annotation);
    }

    public static <T>  Map<String,T> getBeansOfType(Class<T> clazz){
        return applicationContext.getBeansOfType(clazz);
    }

    public static <T>  Map<String,T> getBeansOfType(Class<T> clazz,boolean includeNonSingletons, boolean allowEagerInit){
        return applicationContext.getBeansOfType(clazz,includeNonSingletons,allowEagerInit);
    }

    public static <A extends Annotation> A findAnnotationOnBean(String name,Class<A> clazz){
        return applicationContext.findAnnotationOnBean(name,clazz);
    }
}
