package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.ops.TopicOps;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.StatusListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @autor qiubingyu
 * @ClassName TopicOpsImpl
 * @date 2020/12/24
 **/
@Component
class TopicOpsImpl<M> extends AbstrackRedisOps implements TopicOps<M> {
    @Override
    public List<String> getChannelNames() {
        return null;
    }

    @Override
    public long publish(Object var1) {
        return 0;
    }

    @Override
    public <M1> int addListener(Class<M1> var1, MessageListener<? extends M1> var2) {
        return 0;
    }

    @Override
    public int addListener(StatusListener var1) {
        return 0;
    }

    @Override
    public void removeListener(MessageListener<?> var1) {

    }

    @Override
    public void removeListener(int var1) {

    }

    @Override
    public void removeAllListeners() {

    }

    @Override
    public int countListeners() {
        return 0;
    }
}
