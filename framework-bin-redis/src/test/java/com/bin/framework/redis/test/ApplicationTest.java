package com.bin.framework.redis.test;

import com.bin.framework.redis.config.AutoRedisConfig;
import com.bin.framework.redis.config.RedisConfigProperties;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
