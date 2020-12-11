package com.bin.framework.redis.test;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author qiubingyu
 * @ClassName Application.java
 * @createTime 2020/12/11
 **/
public class Application implements ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .profiles("test")
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }
}
