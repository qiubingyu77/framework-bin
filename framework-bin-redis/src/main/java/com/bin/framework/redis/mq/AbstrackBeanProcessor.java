package com.bin.framework.redis.mq;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @autor qiubingyu
 * @ClassName AbstrackBeanProcessor
 * @date 2020/12/19
 **/
public class AbstrackBeanProcessor implements BeanPostProcessor, ApplicationContextAware {
    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
