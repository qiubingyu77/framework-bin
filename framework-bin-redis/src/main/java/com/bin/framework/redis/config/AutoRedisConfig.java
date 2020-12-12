package com.bin.framework.redis.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

import java.time.Duration;

/**
 * @author qiubingyu
 * @ClassName AutoRedisConfig.java
 * @createTime 2020/12/11
 **/
@Configuration
@ConditionalOnProperty(prefix = "framework.redis", name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = {RedisConfigProperties.class})
public class AutoRedisConfig {

    @Bean
    public JedisShardInfo jedisShardInfo(RedisConfigProperties redisConfigProperties){
        return null;
    }

    @Bean
    public JedisPoolConfig jedisPoolConfig(RedisConfigProperties redisConfigProperties){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfigProperties.getMaxTotal() == 0 ? 8 : redisConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfigProperties.getMaxIdle() == 0 ? 8 : redisConfigProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfigProperties.getMinIdle() == 0 ? 0 : redisConfigProperties.getMinIdle());
        jedisPoolConfig.setTestWhileIdle(true);
        jedisPoolConfig.setTestOnBorrow(true);
        jedisPoolConfig.setTestOnReturn(false);
        return jedisPoolConfig;
    }

    /**
     * redis connection factory
     * @return
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory(RedisConfigProperties redisConfigProperties) {
        RedisStandaloneConfiguration standaloneConfig = null; //单机模式
        RedisSentinelConfiguration sentinelConfig = null;  //哨兵模式
        RedisClusterConfiguration clusterConfig = null;   //集群模式

        String host;
        int port;
        try {
            String[] hostAndPort = redisConfigProperties.getHostNames().split(",");
            hostAndPort = hostAndPort[0].split(":");
             host = hostAndPort[0];
             port = NumberUtils.toInt(hostAndPort[1]);
        }catch (Exception e){
            throw  new RuntimeException("请正确配置redis节点...",e);
        }

        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(redisConfigProperties.getConnectionTimeout()))
                .readTimeout(Duration.ofSeconds(redisConfigProperties.getSoTimeout()))
                .build();

        switch (redisConfigProperties.getMode()){
            case "Sentinel":
                sentinelConfig = new RedisSentinelConfiguration();
                sentinelConfig.setDatabase(redisConfigProperties.getDb());
//                sentinelConfig.setMaster("");
//                sentinelConfig.setSentinels("");
                return new JedisConnectionFactory(sentinelConfig,clientConfig);
            case "Cluster":
                clusterConfig = new RedisClusterConfiguration();
//                clusterConfig.setClusterNodes();
                return new JedisConnectionFactory(clusterConfig,clientConfig);
            case "Standalone":
            default:
                standaloneConfig = new RedisStandaloneConfiguration();
                standaloneConfig.setDatabase(redisConfigProperties.getDb());
                standaloneConfig.setHostName(host);
                standaloneConfig.setPort(port);
                return new JedisConnectionFactory(standaloneConfig,clientConfig);
        }
    }

    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
