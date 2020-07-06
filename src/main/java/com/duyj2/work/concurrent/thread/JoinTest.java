package com.duyj2.work.concurrent.thread;

import java.util.concurrent.TimeUnit;

//让当前线程等待,直到t线程结束后再继续运行
public class JoinTest extends Thread {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new JoinTest();
        t.start();

        //让当前线程等待t线程结束后再继续运行
        t.join();

        //让当前线程等待1000ms, 或t线程结束后再继续运行
        //t.join(1000);

        System.out.println(Thread.currentThread().getName() + " over");
    }

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getName() + " over");
    }
}
