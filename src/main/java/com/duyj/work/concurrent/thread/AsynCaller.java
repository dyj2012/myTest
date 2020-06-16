package com.duyj.work.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class AsynCaller {

    private static ExecutorService pool = Executors.newCachedThreadPool(new MyFactory());

    private static class MyFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(new MyHandler());
            return t;
        }
    }

    private static class MyHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getName() + " " + e.getMessage());
        }
    }
}
