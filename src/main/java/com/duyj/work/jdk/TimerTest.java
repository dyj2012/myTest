package com.duyj.work.jdk;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new Task();

        timer.scheduleAtFixedRate(task, 10, 1000 * 2);


        //timer.cancel();
        timer.purge();
    }


    private static class Task extends TimerTask {

        /**
         * The action to be performed by this timer task.
         */
        @Override
        public void run() {
            System.out.println("haha");
        }
    }
}
