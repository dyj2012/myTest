package com.duyj.work.concurrent.thread;

/**
 * 两个线程交替打印奇数和偶数
 */
public class OddEven {

    private static int i = 1;

    private static Object o = new Object();

    private static Thread t1 = new MyThread("奇 ");
    private static Thread t2 = new MyThread("偶 ");

    public static void main(String[] args) {
        t1.start();
        t2.start();
    }

    private static class MyThread extends Thread {
        MyThread(String name) {
            super(name);
        }

        public void run() {
            try {
                synchronized (o) {
                    while (i < 100) {
                        System.out.println(getName() + i++);
                        //System.out.println(t1.getState() + " " + t2.getState());
                        o.notify();
                        o.wait();
                    }
                    o.notifyAll();
                }
                System.out.println(getName() + " over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
