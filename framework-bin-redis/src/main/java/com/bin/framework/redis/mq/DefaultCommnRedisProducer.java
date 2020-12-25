package com.bin.framework.redis.mq;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @autor qiubingyu
 * @ClassName AbstrackProduce
 * @date 2020/12/24
 **/
@Slf4j
@Component
class DefaultCommnRedisProducer<M> implements Produce<M>{

    @Autowired
    RedissonClient redissonClient;

    Map<String, RTopic> topicMap = new HashMap<>();

    @Override
    public void sendMessag(String topic, M message) {
        topicMap.putIfAbsent(topic,redissonClient.getTopic(topic)).publish(message);
    }
}
