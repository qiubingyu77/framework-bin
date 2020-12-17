package com.bin.framework.redis.strategy;

import lombok.Data;

import java.util.List;

@Data
public class ClusterStrategy {
    private List<String> clusterNodes;
}
