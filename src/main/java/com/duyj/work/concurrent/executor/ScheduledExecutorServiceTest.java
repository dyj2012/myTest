package com.duyj.work.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest{

    private static class RunMe implements Runnable{
        @Override
        public void run(){
            System.out.println("running!");
        }
    }

    public static void main(String[] args) throws InterruptedException{

        ScheduledExecutorService exe = Executors.newScheduledThreadPool(1);

        //延迟执行
        ScheduledFuture sf = exe.schedule(new RunMe(), 1, TimeUnit.SECONDS);

        //定时执行
        ScheduledFuture sf2 = exe.scheduleAtFixedRate(new RunMe(), 1, 5, TimeUnit.SECONDS);

        //定时执行
        ScheduledFuture sf3 = exe.scheduleWithFixedDelay(new RunMe(), 1, 5, TimeUnit.SECONDS);

        TimeUnit.SECONDS.sleep(50);

        exe.shutdown();

    }

}
