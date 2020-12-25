package com.bin.framework.redis.mq;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @autor qiubingyu
 * @ClassName Producer
 * @date 2020/12/25
 **/
@Import(RedisBeanDefinitionRegistrar.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Producer {
    String topic();
}
