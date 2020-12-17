package com.bin.framework.redis.strategy;

import lombok.Data;

import java.util.List;

@Data
public class SentinelStrategy {

    private String master;

    private List<String> hostNames;

    private int db;
}
