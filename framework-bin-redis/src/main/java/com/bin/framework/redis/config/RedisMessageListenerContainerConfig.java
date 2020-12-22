package com.bin.framework.redis.config;

import com.bin.framework.redis.mq.annotation.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @autor qiubingyu
 * @ClassName RedisMessageListenerContainerConfig
 * @date 2020/12/22
 **/
@Configuration
class RedisMessageListenerContainerConfig {

    @Bean
    public Executor executor(){
        return  new ThreadPoolExecutor(3, 5, 10,
                TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>(),r->{
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
        });
    }

    @Bean
    public RedisMessageListenerContainer messageListenerContainer(Map<String,MessageListener> listeners,
                                                                  Executor executor,
                                                                  RedisConnectionFactory redisConnectionFactory){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.setTaskExecutor(executor);
        listeners.values().forEach(messageListener -> {
            Consumer consumer = AnnotationUtils.findAnnotation(messageListener.getClass(), Consumer.class);
            redisMessageListenerContainer.addMessageListener(messageListener,new ChannelTopic(consumer.topic()));
        });
        return redisMessageListenerContainer;
    }

    @Bean
    public Map<String, MessageListener> listeners(Map<String, MessageListener> listenerMap){
        return listenerMap.entrySet().stream()
                .filter(entry -> Objects.nonNull(AnnotationUtils.findAnnotation(entry.getValue().getClass(), Consumer.class)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
