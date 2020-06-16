package com.duyj.work.concurrent.locks;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
读锁 共享锁

写锁 独占锁
 */
public class ReentrantReadWriteLockTest {

    public static void main(String[] args) throws InterruptedException {
        final Queue queue = new Queue();
        //两个线程写，两个线程读
        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        queue.put();
                    }
                }
            }.start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        queue.get();
                    }
                }
            }.start();
        }
    }

    private static class Queue {
        public int n = 0;
        // 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
        private LinkedList<Integer> list = new LinkedList<Integer>();
        private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

        public void get() {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rwlock.readLock().lock();// 上读锁，其他线程只能读不能写
            if (list.size() > 0) {
                int a = list.pop();
                // System.out.println("  take " + a);
            }
            rwlock.readLock().unlock(); // 释放读锁
        }

        public void put() {
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rwlock.writeLock().lock();// 上写锁，不允许其他线程读也不允许写
            this.list.add(n++);
            System.out.println(this.list.toString());
            rwlock.writeLock().unlock();// 释放写锁
        }
    }

}
