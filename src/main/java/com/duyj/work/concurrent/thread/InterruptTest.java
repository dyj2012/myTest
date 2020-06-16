package com.duyj.work.concurrent.thread;

public class InterruptTest extends Thread {

    public static void main(String[] args) {
        try {
            Thread t = new InterruptTest();
            System.out.println("1 (" + t.getState() + ") is new.");

            t.start();                      // 启动“线程t1”
            System.out.println("2 (" + t.getState() + ") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(300);
            t.interrupt();
            System.out.println("3 (" + t.getState() + ") is interrupted.");

            // 主线程休眠300ms，然后查看t1的状态。
            Thread.sleep(100);
            System.out.println("4 (" + t.getState() + ")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            int i = 0;
            while (!isInterrupted()) {
                Thread.sleep(50);
                System.out.println("(" + this.getState() + ") loop " + i++);
            }
        } catch (InterruptedException e) {
            System.out.println("(" + this.getState() + ") catch InterruptedException.");
        }
        System.out.println("over");
    }

}
