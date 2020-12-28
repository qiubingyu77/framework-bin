package com.bin.framework.redis.mq;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiubingyu
 * @ClassName ProduceAutoProcess.java
 * @createTime 2020/12/25
 **/
@Component
public class ProduceAutoProcess implements BeanFactoryPostProcessor {

//    @Producer(topic = "xxx")
//    @Autowired
//    Produce<Produce.User> produce;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
      /*  beanFactory.getBeanNamesIterator().forEachRemaining(n->{
            MutablePropertyValues propertyValues = beanFactory.getBeanDefinition(n).getPropertyValues();
            List<PropertyValue> propertyValueList = propertyValues.getPropertyValueList();
        });*/
    }
}
