package com.bin.framework.redis;

import com.bin.framework.redis.config.ApplicationTest;
import org.junit.Before;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @autor qiubingyu
 * @ClassName BaseTest
 * @date 2020/12/21
 **/
public abstract class BaseTest implements ApplicationRunner {

    protected ConfigurableApplicationContext applicationContext;

    @Before
    public void start() {
        applicationContext = new SpringApplicationBuilder()
                .sources(ApplicationTest.class)
                .profiles("test")
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.CONSOLE)
                .run("");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
