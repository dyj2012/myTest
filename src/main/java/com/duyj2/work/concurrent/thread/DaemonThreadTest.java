package com.duyj2.work.concurrent.thread;

import java.util.concurrent.TimeUnit;

//后台线程不影响父线程的结束
public class DaemonThreadTest extends Thread {

    public static void main(String[] args) {
        Thread t = new DaemonThreadTest();
        t.setDaemon(true);
        t.start();
        System.out.println("main end");
    }

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("thread end");
        }
    }
}
