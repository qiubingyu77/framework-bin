package com.bin.framework.redis.dto;

import lombok.Data;

import java.util.List;

@Data
public class SentinelStrategy extends RedisStrategy{

    public SentinelStrategy() {
        super();
    }

    public SentinelStrategy(String name) {
        super(name);
    }

    private String masterHostName;

    private List<String> hostNames;
}
