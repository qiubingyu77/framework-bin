package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.mq.Produce;
import com.bin.framework.redis.mq.Producer;
import org.springframework.stereotype.Component;

@Component
public class Test {
    @Producer(topic = "xxx")
    Produce<String> produce;
}
