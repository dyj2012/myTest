package com.duyj2.work.concurrent.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印奇数和偶数
 */
public class OddEven2 {

    private static Lock lock = new ReentrantLock();
    static Condition even_c = lock.newCondition();
    static Condition odd_c = lock.newCondition();

    public static void main(String[] args) {
        new Even().start();
        new Odd().start();
    }

    private static class Odd extends Thread {
        public void run() {
            int odd = 1;
            try {
                lock.lock();
                while (odd < 100) {
                    System.out.println("奇 " + odd);
                    odd += 2;
                    even_c.signal();
                    odd_c.await();
                }
                System.out.println("奇 over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                even_c.signal();
                lock.unlock();
            }
        }
    }

    private static class Even extends Thread {
        public void run() {
            int even = 0;
            try {
                lock.lock();
                while (even < 100) {
                    System.out.println("偶 " + even);
                    even += 2;
                    odd_c.signal();
                    even_c.await();
                }
                System.out.println("偶 over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                odd_c.signal();
                lock.unlock();
            }
        }
    }
}
