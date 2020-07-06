package com.duyj2.work.concurrent.collection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

/*

 */
public class SynchronousQueueTest {

    private static BlockingQueue<String> queue = new SynchronousQueue<>();

    private static CountDownLatch end = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        end.await();
    }

    private static class Producer implements Runnable {
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    queue.put(i + "");
                    System.out.println("put -> [" + i + "]");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                end.countDown();
                System.out.println("Producer Over");
            }
        }
    }

    private static class Consumer implements Runnable {
        public void run() {
            try {
                while (true) {
                    String c = queue.take();
                    System.out.println("take -> [" + c + "]");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                end.countDown();
                System.out.println("Consumer Over");
            }
        }
    }


}
