package com.bin.framework.redis.mq.produce;

import com.bin.framework.common.json.JsonUtil;
import com.bin.framework.redis.ops.ListOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @autor qiubingyu
 * @ClassName DefaultProducer
 * @date 2020/12/20
 **/
@Component
class DefaultProducer<T extends Serializable> implements IProduce<T>{

    @Autowired
    ListOps<String,T> listOps;

    @Override
    public void sendMessage(String topic, T message) {
        Optional.ofNullable(message).ifPresent(m-> listOps.leftPush(PRE_TOPIC+topic,message));
    }

    @Override
    public void sendMessages(String topic, List<T> messages) {
        Optional.ofNullable(messages).ifPresent(ms-> listOps.leftPushAll(PRE_TOPIC+topic,messages) );
    }
}
