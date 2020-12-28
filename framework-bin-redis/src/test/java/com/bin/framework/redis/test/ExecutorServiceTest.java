package com.bin.framework.redis.test;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTest {

    @Test
    public void test1() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
//                throw new RuntimeException("这是第"+Thread.currentThread().getName()+"异常");
                System.out.println("这是第" + Thread.currentThread().getName() + "异常");
            });
        }

    }

    @Test
    public void test4() {
    }

    @Test
    public void test2() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        for (int i = 0; i < 100; i++) {
            treeMap.put(Integer.toString(i), Integer.toString(i));
        }
    }
}
