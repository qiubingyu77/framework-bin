package com.bin.framework.redis.dto;

import lombok.Data;

import java.util.List;

@Data
public class ClusterStrategy extends  RedisStrategy{

    public ClusterStrategy() {
    }

    public ClusterStrategy(String name) {
        super(name);
    }

    private List<String> clusterNodes;
}
