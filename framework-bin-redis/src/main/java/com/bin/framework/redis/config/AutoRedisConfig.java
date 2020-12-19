package com.bin.framework.redis.config;

import com.bin.framework.redis.strategy.ClusterStrategy;
import com.bin.framework.redis.strategy.SentinelStrategy;
import com.bin.framework.redis.strategy.StandaloneStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author qiubingyu
 * @ClassName AutoRedisConfig.java
 * @createTime 2020/12/11
 **/
@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.bin.framework.redis"})
@EnableConfigurationProperties(value = {RedisConfigProperties.class})
@ConditionalOnProperty(prefix = "framework.redis", name = "enable", havingValue = "true")
class AutoRedisConfig {

   /* @Bean
    public JedisShardInfo jedisShardInfo(RedisConfigProperties redisConfigProperties){
        return null;
    }*/

    @Bean
    public JedisPoolConfig jedisPoolConfig(RedisConfigProperties redisConfigProperties){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfigProperties.getMaxTotal() == 0 ? JedisPoolConfig.DEFAULT_MAX_TOTAL : redisConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfigProperties.getMaxIdle() == 0 ? JedisPoolConfig.DEFAULT_MAX_IDLE : redisConfigProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisConfigProperties.getMinIdle() == 0 ? JedisPoolConfig.DEFAULT_MIN_IDLE : redisConfigProperties.getMinIdle());
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
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder()
                .connectTimeout(Duration.ofSeconds(redisConfigProperties.getConnectionTimeout()))
                .readTimeout(Duration.ofSeconds(redisConfigProperties.getSoTimeout()))
                .usePooling()//使用连接池
                .poolConfig(jedisPoolConfig)
                .build();
        return Optional.of(redisConfigProperties).map(redisConfig->{
            StandaloneStrategy standalone;
            if (Objects.nonNull(standalone = redisConfig.getStandalone())){
                Assert.notNull(redisConfig.getStandalone().getHostName(),"standalone模式请配置hostName");
                RedisStandaloneConfiguration standaloneConfig; //单机模式
                String[] hostName = StringUtils.split(standalone.getHostName(), ":");
                standaloneConfig =  new RedisStandaloneConfiguration(hostName[0],NumberUtils.toInt(hostName[1]));
                standaloneConfig.setDatabase(redisConfigProperties.getStandalone().getDb());
                return new JedisConnectionFactory(standaloneConfig,clientConfig);
            }
            ClusterStrategy cluster = redisConfig.getCluster();
            if (Objects.nonNull(cluster)){
                List<RedisNode> clusterNodes = cluster.getClusterNodes().stream().map(n -> {
                    String[] hostName = StringUtils.split(n, ":");
                    return new RedisNode(hostName[0],NumberUtils.toInt(hostName[1]));
                }).collect(Collectors.toList());
                RedisClusterConfiguration clusterConfig = new RedisClusterConfiguration();
                clusterConfig.setClusterNodes(clusterNodes);
                clusterConfig.setMaxRedirects(16);
                return new JedisConnectionFactory(clusterConfig,clientConfig);
            }
            SentinelStrategy sentinel = redisConfig.getSentinel();
            if (Objects.nonNull(sentinel)){
                RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration(); //单机模式
                sentinelConfig.setMaster(sentinel.getMaster());
                List<RedisNode> sentinels = cluster.getClusterNodes().stream().map(n -> {
                    String[] hostName = StringUtils.split(n, ":");
                    return new RedisNode(hostName[0],NumberUtils.toInt(hostName[1]));
                }).collect(Collectors.toList());
                sentinelConfig.setSentinels(sentinels);
                sentinelConfig.setDatabase(sentinel.getDb());
                return new JedisConnectionFactory(sentinelConfig,clientConfig);
            }
            throw new RuntimeException("请配置redis连接模式......");
        }).get();
    }

    @Bean
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        return redisTemplate;
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> ValueOperations<K,V> valueOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForValue();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> ListOperations<K, V> listOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForList();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> SetOperations<K,V> setOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForSet();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K> StreamOperations<K, ?, ?> streamOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForStream();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> ZSetOperations<K,V> zSetOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForZSet();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> GeoOperations<K,V> geoOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForGeo();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> HyperLogLogOperations<K,V> hyperLogLogOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForHyperLogLog();
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public <K,V> ClusterOperations<K,V> clusterOperations(RedisTemplate redisTemplate){
        return redisTemplate.opsForCluster();
    }


}
