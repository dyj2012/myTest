package com.duyj2.work.concurrent.locks;

import com.duyj2.work.utils.Q;

import java.util.concurrent.TimeUnit;

public class DeadLock extends Thread {
    private Object lock1;
    private Object lock2;

    public DeadLock(Object o1, Object o2) {
        this.lock1 = o1;
        this.lock2 = o2;
    }

    public static void main(String[] args) {
        Object o1 = new Object(), o2 = new Object();
        new DeadLock(o1, o2).start();
        new DeadLock(o2, o1).start();
        Q.p("main end");
    }

    public void run() {
        synchronized (lock1) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                Q.p("end");
            }
        }
    }
}

