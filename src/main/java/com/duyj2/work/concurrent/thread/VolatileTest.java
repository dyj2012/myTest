package com.duyj2.work.concurrent.thread;

public class VolatileTest {

    private boolean stop = false;

    private int i = 0;

    public static void main(String[] args) throws InterruptedException {
        VolatileTest test = new VolatileTest();
        T t = test.new T();
        t.start();

        Thread.sleep(1000 * 2);
        //test.stop();

    }

    public void stop() {
        this.stop = true;
    }

    private class T extends Thread {

        T() {
            this.setDaemon(false);
        }

        public void run() {
            while (!stop) {
                System.out.println(i++);
            }
        }
    }
}
