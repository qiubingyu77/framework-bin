package com.bin.framework.redis.dto;

import lombok.Data;

@Data
public class StandaloneStrategy extends RedisStrategy {

    public StandaloneStrategy() {
    }

    public StandaloneStrategy(String name) {
        super(name);
    }

    private String HostName;

}
