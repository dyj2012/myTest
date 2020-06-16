package com.duyj.work.concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class CachedThreadPoolTest {

    private static AtomicLong id = new AtomicLong(0);

    public static void main(String[] args) {
        ExecutorService exe = Executors.newCachedThreadPool();

        for (int i = 0; i < 20; i++) {
            exe.submit(new MyRunnable());
        }
    }

    private static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(id.incrementAndGet() + " start");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
