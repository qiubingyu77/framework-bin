package com.bin.framework.redis.mq;

import com.bin.framework.redis.BaseTest;
import com.bin.framework.redis.ops.impl.TestProduce;
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
        TestProduce bean = applicationContext.getBean(TestProduce.class);
        boolean qby = bean.getProduce().sendMessag("qby");
        System.out.println(qby);
    }

    @Test
    public void postProcessBeanFactory() {
    }
}