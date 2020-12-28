package com.bin.framework.common.utils;

import java.util.concurrent.ThreadFactory;

/**
 * @autor qiubingyu
 * @ClassName ThreadFactory
 * @date 2020/12/23
 **/
public class DefaultThreadFactory implements ThreadFactory {

    private static ThreadFactory defaultThreadFactory = new DefaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return null;
    }

    public static ThreadFactory defaultThreadFactory() {
        return defaultThreadFactory;
    }
}
