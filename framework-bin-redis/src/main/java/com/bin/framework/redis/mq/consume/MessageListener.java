package com.bin.framework.redis.mq.consume;

import org.springframework.data.redis.connection.Message;

/**
 * @author qiubingyu
 * @ClassName MessageListener.java
 * @createTime 2020/12/22
 **/
public interface MessageListener {
    void onMessage(Message message);
}
