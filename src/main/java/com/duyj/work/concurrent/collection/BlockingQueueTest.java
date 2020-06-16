package com.duyj.work.concurrent.collection;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * //固定大小
 * BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
 * <p>
 * //动态大小
 * BlockingQueue<String> queue2 = new LinkedBlockingQueue<String>();
 * <p>
 * //有优先级的阻塞队列
 * BlockingQueue<String> queue3 = new PriorityBlockingQueue();
 * <p>
 * //队列中只能有一个元素
 * BlockingQueue<String> queue4 = new SynchronousQueue();
 */
public class BlockingQueueTest {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
//    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>(10);
//    private static BlockingQueue<String> queue = new PriorityBlockingQueue<>(10);
//    private static BlockingQueue<String> queue = new SynchronousQueue();

    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Producer().start();
        new Consumer().start();
        latch.await();
    }

    private static class Producer extends Thread {
        public void run() {
            try {
                while (true) {
                    char c = (char) System.in.read();
                    queue.put(c + "");
                    if (c == 'Q') {
                        break;
                    }
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }

    private static class Consumer extends Thread {
        public void run() {
            try {
                while (true) {
                    String c = queue.take();
                    System.out.println("get -> [" + c + "]");
                    if (c.equals("Q")) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }

}

