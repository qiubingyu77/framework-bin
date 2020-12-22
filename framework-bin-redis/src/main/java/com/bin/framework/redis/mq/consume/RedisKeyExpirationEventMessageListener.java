package com.bin.framework.redis.mq.consume;

import com.bin.framework.redis.mq.annotation.KeyExpir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author qiubingyu
 * @ClassName RedisKeyExpirationEventMessageListener.java
 * @createTime 2020/12/22
 **/
@Component
@ConditionalOnBean(value = {MessageListener.class,RedisMessageListenerContainer.class})
class RedisKeyExpirationEventMessageListener extends KeyExpirationEventMessageListener {

    @Autowired
    Map<String,MessageListener> beanKeyExpMap;

    Map<String,MessageListener> registerkeyExpMap;

    Executor executor = Executors.newFixedThreadPool(3, (r)->{
        Thread thread = new Thread(r);
        thread.setDaemon(true); //设置守护线程，随主进程关闭而关闭
        return thread;
    });

    public RedisKeyExpirationEventMessageListener(@Autowired RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    protected void doHandleMessage(Message message) {
        executor.execute(()->{
            try {
                Optional.ofNullable(registerkeyExpMap.get(new String(message.getChannel()))).ifPresent(messageListener -> {
                    messageListener.onMessage(message);
                });
            }catch (Throwable e){ //捕获异常，不会因为异常原因导致线程池增减消耗资源
                e.printStackTrace();
            }
        });
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        registerkeyExpMap = Optional.ofNullable(beanKeyExpMap).map(kem->
                kem.values().stream()
                        .filter(entry ->  //获取有KeyExpir注解并实现MessageListener的bean
                                Objects.nonNull(AnnotationUtils.findAnnotation(entry.getClass(), KeyExpir.class))
                               && entry instanceof MessageListener
                        )                                 //获取指定的过期key
                        .collect(Collectors.toMap(k -> AnnotationUtils.findAnnotation(k.getClass(), KeyExpir.class).key(), v ->v))
        ).orElseGet(HashMap::new);
    }
}
