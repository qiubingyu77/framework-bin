package com.bin.framework.redis.ops;

import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.StatusListener;

import java.util.List;

/**
 * @autor qiubingyu
 * @ClassName TopicOps
 * @date 2020/12/24
 **/
public interface TopicOps<M> {

    List<String> getChannelNames();

    long publish(Object var1);

    <M> int addListener(Class<M> var1, MessageListener<? extends M> var2);

    int addListener(StatusListener var1);

    void removeListener(MessageListener<?> var1);

    void removeListener(int var1);

    void removeAllListeners();

    int countListeners();
}
