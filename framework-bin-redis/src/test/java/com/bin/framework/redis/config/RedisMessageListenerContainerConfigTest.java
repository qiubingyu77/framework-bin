package com.bin.framework.redis.config;

import com.bin.framework.common.context.SpringContainer;
import com.bin.framework.redis.BaseTest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @autor qiubingyu
 * @ClassName RedisMessageListenerContainerConfigTest
 * @date 2020/12/22
 **/
public class RedisMessageListenerContainerConfigTest extends BaseTest {

    @Test
    public void listenerMap() {
        final Object listenerMap = SpringContainer.getBean("listenerMap");
        System.out.println(listenerMap);
    }

    @Test
    public void listeners() {
    }
}