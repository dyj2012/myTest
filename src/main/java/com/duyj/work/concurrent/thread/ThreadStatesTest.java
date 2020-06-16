package com.duyj.work.concurrent.thread;

/**
 * NEW,
 * RUNNABLE,
 * BLOCKED,
 * WAITING,
 * TIMED_WAITING,
 * TERMINATED;
 */
public class ThreadStatesTest extends Thread {

    private Object o = new Object();

    public static void main(String[] args) throws InterruptedException {
        for (State state : State.values()) {
            System.out.println(state.name());
        }
        System.out.println();

        Thread t = new ThreadStatesTest();
        Thread t2 = new ThreadStatesTest();
        System.out.println("after new: " + t.getState());
        t.start();
        t2.start();
        System.out.println("after start: " + t.getState());
        System.out.println("after t2 start: " + t2.getState());
        Thread.sleep(10);
        System.out.println("after start: " + t.getState());
        System.out.println("after t2 start: " + t2.getState());
        Thread.sleep(1000);
        System.out.println("sleep:" + t.getState());
        Thread.sleep(1000);

        System.out.println("over:" + t.getState());

        //Error: java.lang.IllegalThreadStateException
        //t.start();
    }

    public void run() {
        int i = 0;
        synchronized (o) {
            try {
                while (i < 100000) {
                    i++;
                }
                Thread.sleep(2000);
                //this.wait();

                System.out.println(getName() + " over");
            } catch (InterruptedException e) {
            }
        }
    }
}
