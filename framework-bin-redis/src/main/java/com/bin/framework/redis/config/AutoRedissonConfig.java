package com.bin.framework.redis.config;

import com.bin.framework.common.utils.DefaultThreadFactory;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.bin.framework.redis.config.RedisMode.*;

/**
 * @autor qiubingyu
 * @ClassName AutoRedissonConfig
 * @date 2020/12/23
 **/
@Configuration
@ComponentScan(basePackages = {"com.bin.framework.redis"})
@EnableConfigurationProperties(value = {RedisConfigProperties.class})
@ConditionalOnProperty(prefix = "framework.redis", name = "enable", havingValue = "true")
public class AutoRedissonConfig {

    private static final String REDIS_PRE = "redis://";

    @Bean
    public Config config(RedisConfigProperties redisConfigProperties) {
        Config config = new Config();
        ExecutorService excutor = new ThreadPoolExecutor(8, 16, 600, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), DefaultThreadFactory.defaultThreadFactory());
        config.setExecutor(excutor);
//        config.setThreads(2);
//        config.setNettyThreads(2);
        config.setCodec(new JsonJacksonCodec());
        switch (redisConfigProperties.getRedisMode()) {
            case SINGLE:
                final SingleServerConfig singleServerConfig = config.useSingleServer();
                singleServerConfig.setKeepAlive(true);
                singleServerConfig.setConnectionPoolSize(redisConfigProperties.getMaxTotal());
                singleServerConfig.setConnectionMinimumIdleSize(redisConfigProperties.getMinIdle());
                singleServerConfig.setAddress(REDIS_PRE + redisConfigProperties.getMaster());
                singleServerConfig.setDatabase(redisConfigProperties.getDb());
                singleServerConfig.setConnectTimeout(redisConfigProperties.getConnectionTimeout());
                singleServerConfig.setTimeout(redisConfigProperties.getSoTimeout());
                break;
            case SENTINEL:
                final SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
                sentinelServersConfig.setKeepAlive(true);
                sentinelServersConfig.setDatabase(redisConfigProperties.getDb());
                sentinelServersConfig.setConnectTimeout(redisConfigProperties.getConnectionTimeout());
                sentinelServersConfig.setTimeout(redisConfigProperties.getSoTimeout());
                sentinelServersConfig.setMasterName(REDIS_PRE + redisConfigProperties.getMaster());
                sentinelServersConfig.setMasterConnectionPoolSize(redisConfigProperties.getMaxTotal());
                sentinelServersConfig.setMasterConnectionMinimumIdleSize(redisConfigProperties.getMinIdle());
                sentinelServersConfig.setSlaveConnectionPoolSize(redisConfigProperties.getMaxTotal());
                sentinelServersConfig.setSlaveConnectionMinimumIdleSize(redisConfigProperties.getMinIdle());
                sentinelServersConfig.setReadMode(ReadMode.MASTER_SLAVE);
                final List<String> sentinelNodes = redisConfigProperties.getRedisNodes().stream().map(n -> REDIS_PRE + n).collect(Collectors.toList());
                sentinelServersConfig.addSentinelAddress(sentinelNodes.toArray(new String[]{}));
                break;
            case CLUSTER:
                final ClusterServersConfig clusterServersConfig = config.useClusterServers();
                clusterServersConfig.setKeepAlive(true);
                clusterServersConfig.setConnectTimeout(redisConfigProperties.getConnectionTimeout());
                clusterServersConfig.setTimeout(redisConfigProperties.getSoTimeout());
                clusterServersConfig.setMasterConnectionPoolSize(redisConfigProperties.getMaxTotal());
                clusterServersConfig.setMasterConnectionMinimumIdleSize(redisConfigProperties.getMinIdle());
                clusterServersConfig.setSlaveConnectionPoolSize(redisConfigProperties.getMaxTotal());
                clusterServersConfig.setSlaveConnectionMinimumIdleSize(redisConfigProperties.getMinIdle());
                clusterServersConfig.setReadMode(ReadMode.MASTER_SLAVE);
                final List<String> clusterNodes = redisConfigProperties.getRedisNodes().stream().map(n -> REDIS_PRE + n).collect(Collectors.toList());
                clusterServersConfig.addNodeAddress(clusterNodes.toArray(new String[]{}));
                break;
            default:
                throw new RuntimeException("请配置redis模式");
        }
        return config;
    }

    @Bean
    @ConditionalOnBean(Config.class)
    public RedissonClient redissonClient(Config config) {
        return Redisson.create(config);
    }
}
