package com.duyj2.work.concurrent.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数
 */
public class SemaphoreTest {

    private static final int LIMIT = 3;

    private static final Semaphore semaphore = new Semaphore(LIMIT);

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 9; i++) {
            service.execute(new MyThread());
        }
        service.shutdown();
    }

    private static class MyThread extends Thread {
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(getName() + " begin " + "running:" + (LIMIT - semaphore.availablePermits()) + " waiting:" + semaphore.getQueueLength());
                Thread.sleep((long) (Math.random() * 200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                //System.out.println(Thread.currentThread().getName() + " end   " + "running:" + (1 - semaphore.availablePermits()) + " waiting:" + semaphore.getQueueLength());
            }
        }
    }
}
