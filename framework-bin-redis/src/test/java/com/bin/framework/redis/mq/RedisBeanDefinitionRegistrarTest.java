package com.bin.framework.redis.mq;

import com.bin.framework.redis.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @autor qiubingyu
 * @ClassName RedisBeanDefinitionRegistrarTest
 * @date 2020/12/25
 **/
public class RedisBeanDefinitionRegistrarTest extends BaseTest {

    @Test
    public void postProcessBeanDefinitionRegistry() {
        RedisBeanDefinitionRegistrar bean = applicationContext.getBean(RedisBeanDefinitionRegistrar.class);
    }

    @Test
    public void postProcessBeanFactory() {
    }
}