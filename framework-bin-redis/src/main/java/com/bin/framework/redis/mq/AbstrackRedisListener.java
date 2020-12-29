package com.bin.framework.redis.mq;

import com.bin.framework.redis.mq.anno.Listener;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

/**
 * @author qiubingyu
 * @ClassName AbstrackRedisListener.java
 * @createTime 2020/12/29
 **/
public abstract class AbstrackRedisListener<M> implements MessageListener<M>, InitializingBean, DisposableBean {

    @Autowired
    protected RedissonClient redissonClient;

    private RTopic rTopic;

    protected abstract void onMessage(M msg);

    @Override
    public final void onMessage(CharSequence channel, M msg) {
        onMessage(msg);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            Class<M> clazz = (Class<M>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            Listener annotation = AnnotationUtils.findAnnotation(getClass(), Listener.class);
            Optional.ofNullable(annotation).ifPresent(a->{
                this.rTopic = redissonClient.getTopic(a.topic());
                this.rTopic.addListenerAsync(clazz, this);
            });
        }catch (ClassCastException e){
            Assert.isTrue(false, "请定义泛型类...");
        }
    }

    @Override
    public final void destroy() throws Exception {
        this.rTopic.removeListener(this);
    }
}
