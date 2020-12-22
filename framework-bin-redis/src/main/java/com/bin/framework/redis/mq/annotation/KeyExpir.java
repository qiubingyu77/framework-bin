package com.bin.framework.redis.mq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * redis key 过期监听
 * @author qiubingyu
 * @ClassName KeyExpir.java
 * @createTime 2020/12/22
 **/
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface KeyExpir {
    String key();
}
