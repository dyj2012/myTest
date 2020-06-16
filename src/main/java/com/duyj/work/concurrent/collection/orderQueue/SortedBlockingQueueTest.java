package com.duyj.work.concurrent.collection.orderQueue;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SortedBlockingQueueTest {

    private static SortedBlockingQueue queue = new SortedBlockingQueue();

    private static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        latch.await();
    }

    private static class Producer implements Runnable {
        public void run() {
            try {
                Scanner scanner = new Scanner(System.in);
                while (scanner.hasNextLine()) {
                    int c = Integer.valueOf(scanner.nextLine().trim());
                    Node node = new Node(c, c + "");
                    queue.put(node);
                }
            } finally {
                latch.countDown();
            }
        }
    }

    private static class Consumer implements Runnable {
        int i = 1;

        public void run() {
            try {
                while (true) {
                    Node c = queue.offer(i++);
                    System.out.println(c.toString());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }
    }

}