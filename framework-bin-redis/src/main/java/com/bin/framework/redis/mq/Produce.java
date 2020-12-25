package com.bin.framework.redis.mq;

/**
 * @autor qiubingyu
 * @ClassName Produce
 * @date 2020/12/24
 **/
public interface Produce<M> {

    void sendMessag(String topic,M message);

}
