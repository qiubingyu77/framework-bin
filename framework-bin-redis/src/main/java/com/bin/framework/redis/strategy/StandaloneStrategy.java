package com.bin.framework.redis.strategy;

import lombok.Data;

@Data
public class StandaloneStrategy  {
    private String hostName;
    private int db;
}
