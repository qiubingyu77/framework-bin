package com.bin.framework.redis.mq.produce;

import java.io.Serializable;
import java.util.List;

/**
 * @autor qiubingyu
 * @ClassName IProduce
 * @date 2020/12/19
 **/
public interface IProduce<T extends Serializable> {

    String PRE_TOPIC = "topic-";

    /**
     * 发送消息到主题
     * @param message
     */
    void sendMessage(String topic,T message);

    /**
     * 批量发送消息
     * @param message
     */
    void sendMessages(String topic,List<T> message);
}
