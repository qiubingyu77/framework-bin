package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.mq.AbstrackRedisListener;
import com.bin.framework.redis.mq.Produce;
import com.bin.framework.redis.mq.anno.Listener;
import com.bin.framework.redis.mq.anno.Producer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Listener(topic = "news")
@Data
@Component
public class TestProduce extends AbstrackRedisListener<TestProduce.User> {

    @Producer(topic = "news")
    Produce<User> produce;

    @Override
    protected void onMessage(User msg) {
        System.out.println(msg);
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class User{
        String username;
        String password;
    }
}
