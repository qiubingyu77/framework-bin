package com.bin.framework.redis.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author qiubingyu
 * @ClassName AutoRedisConfig.java
 * @createTime 2020/12/11
 **/
@Configuration
@ConditionalOnProperty(prefix = "framework.redis", name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = {RedisConfigProperties.class})
public class AutoRedisConfig {




    /**
     * redis connection factory
     * @return
     */
    @Bean
    @ConditionalOnBean
    public RedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = null;
        return null;
    }

}
