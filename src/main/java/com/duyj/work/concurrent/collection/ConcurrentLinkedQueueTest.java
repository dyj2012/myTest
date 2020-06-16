package com.duyj.work.concurrent.collection;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * 基于链接的线程安全的队列FIFO，它适用于高并发的场景,内部通过CAS操作来完成，不会阻塞，元素不可为null
 */
public class ConcurrentLinkedQueueTest {

    private static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue();

    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Producer().start();
        new Consumer().start();
        latch.await();
    }

    private static class Producer extends Thread {
        public void run() {
            int i = 10;
            while (i > 0) {
                queue.add(i-- + ""); //==offer
                System.out.println("set -> [" + i + "]");
            }
            latch.countDown();
        }
    }

    private static class Consumer extends Thread {
        public void run() {
            int i = 10;
            while (i-- > 0) {
                String c = queue.poll();
                System.out.println("get -> [" + c + "]");
            }
            latch.countDown();
        }
    }

}

