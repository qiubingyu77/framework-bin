package com.bin.framework.redis.config;

import com.bin.framework.common.context.SpringContainer;
import com.bin.framework.redis.mq.annotation.Consumer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.List;
import java.util.Map;

/**
 * @autor qiubingyu
 * @ClassName RedisMessageListenerContainerConfig
 * @date 2020/12/22
 **/
@Configuration
class RedisMessageListenerContainerConfig {

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(){
        return null;
    }

    @Bean
    public Map<String, MessageListener> listeners(Map<String, MessageListener> listenerMap){
        return null;
    }

}
