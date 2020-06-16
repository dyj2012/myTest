package com.duyj.work.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/*
 * ReentrantLock用来对一段代码上锁，可以代替synchronized关键字。
 */
public class ReentrantLockTest {

    //A ReentrantLock is owned by the thread last successfully locking, but not yet unlocking it.
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        final int COUNT = 10;
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch doneSignal = new CountDownLatch(COUNT);

        final SomeClass someClass = new SomeClass();

        for (int i = 0; i < COUNT; ++i) {
            final int index = i;
            new Thread() {
                @Override
                public void run() {
                    try {
                        startSignal.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for (int j = 0; j < 1000; ++j) {
                        someClass.test();
                    }
                    System.out.println("running thread " + index);
                    doneSignal.countDown();
                }
            }.start();
        }

        startSignal.countDown();
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count1:" + someClass.get1());
        System.out.println("count2:" + someClass.get2());
    }

    private static class SomeClass {

        private long count1 = 0;
        private long count2 = 0;

        public void test() {

            //++并非原子操作,此处未上锁
            count1++;

            lock.lock();
            //此处线程安全
            count2++;
            //将unlock放在finally块里面
            lock.unlock();
        }

        public long get1() {
            return count1;
        }

        public long get2() {
            return count2;
        }
    }

}
