package com.bin.framework.redis.mq;

import com.bin.framework.redis.BaseTest;
import com.bin.framework.redis.ops.impl.TestProduce;
import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

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
        boolean qby = bean.getProduce().sendMessag(new TestProduce.User("qby","123"));
        System.out.println(qby);
        LockSupport.park();
    }

    @Test
    public void postProcessBeanFactory() {
    }
}