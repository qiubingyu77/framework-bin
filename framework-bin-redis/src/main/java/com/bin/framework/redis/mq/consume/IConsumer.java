package com.bin.framework.redis.mq.consume;

import java.io.Serializable;

/**
 * @autor qiubingyu
 * @ClassName IConsumer
 * @date 2020/12/19
 **/
public interface IConsumer <T extends Serializable>{

    void onMessage(T t);
}
