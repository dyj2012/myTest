package com.duyj2.work.concurrent.thread;

public class ThreadGroupTest {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("线程组");
        for (int i = 0; i < 3; i++) {
            Thread t = new MyThread(tg, "线程" + i);
            t.start();
        }
        System.out.println("activeCount:" + tg.activeCount());
        Thread.sleep(3000);
        tg.interrupt();
        System.out.println("interrupt");
    }

    private static class MyThread extends Thread {
        public MyThread(ThreadGroup tg, String name) {
            super(tg, name);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + "开始");
            while (!this.isInterrupted()) {
            }
            System.out.println(Thread.currentThread().getName() + "结束");
        }
    }
}
