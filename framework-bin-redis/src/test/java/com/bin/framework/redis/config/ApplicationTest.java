package com.bin.framework.redis.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qiubingyu
 * @ClassName ApplicationTest.java
 * @createTime 2020/12/11
 **/
@ComponentScan(basePackages = "com.bin.framework.redis")
public class ApplicationTest implements ApplicationRunner {

    ConfigurableApplicationContext applicationContext;


    @Before
    public void start(){
        applicationContext = new SpringApplicationBuilder()
                .sources(ApplicationTest.class)
                .profiles("test")
                .bannerMode(Banner.Mode.CONSOLE)
                .run("");
    }

    @Test
    public void test1(){
        AutoRedisConfig autoRedisConfig = applicationContext.getBean(AutoRedisConfig.class);
        System.out.println(autoRedisConfig);
        RedisConfigProperties redisConfigProperties = applicationContext.getBean(RedisConfigProperties.class);
        System.out.println(redisConfigProperties.isEnable());
    }

    @Test
    public void test2(){
        RedisConnectionFactory redisConnectionFactory = applicationContext.getBean(RedisConnectionFactory.class);
        for (int i = 0; i < 10 ;i++){
            RedisConnection connection = redisConnectionFactory.getConnection();
            System.out.println(connection);
        }
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
