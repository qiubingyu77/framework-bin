package com.bin.framework.redis.test;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    @Test
    public void test1(){
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i= 0;i < 10;i++){
            executorService.execute(()->{
//                throw new RuntimeException("这是第"+Thread.currentThread().getName()+"异常");
                System.out.println("这是第"+Thread.currentThread().getName()+"异常");
            });
        }

    }
}
