package com.bin.framework.redis.mq.produce;

import com.bin.framework.common.json.JsonUtil;
import com.bin.framework.redis.ops.ListOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @autor qiubingyu
 * @ClassName DefaultProducer
 * @date 2020/12/20
 **/
@Component
class DefaultCommonProducer<T extends Serializable> implements IProduce<T>{

    @Autowired
    ListOps<String,String> listOps;

    @Override
    public void sendMessage(String topic, T message) {
        Assert.notNull(topic,"topic is required...");
        Optional.ofNullable(message).ifPresent(m-> listOps.leftPush(PRE_TOPIC+topic, JsonUtil.bean2json(message)));
    }

    @Override
    public void sendMessages(String topic, List<T> messages) {
        Optional.ofNullable(messages).ifPresent(ms-> ms.forEach(m -> sendMessage(topic,m)));
    }
}
