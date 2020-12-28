package com.bin.framework.redis.ops.impl;

import com.bin.framework.redis.mq.Produce;
import com.bin.framework.redis.mq.Producer;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TestProduce {
    @Producer(topic = "news")
    Produce<String> produce;
}
