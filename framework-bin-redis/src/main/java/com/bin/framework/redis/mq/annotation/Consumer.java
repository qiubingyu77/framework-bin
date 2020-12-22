package com.bin.framework.redis.mq.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @autor qiubingyu
 * @ClassName Consumer
 * @date 2020/12/19
 **/
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Consumer {
    String topic();
}
