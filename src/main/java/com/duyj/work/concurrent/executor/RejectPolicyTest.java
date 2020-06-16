package com.duyj.work.concurrent.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
DiscardPolicy 直接丢弃
DiscardOldestPolicy 丢弃等待最久的（队列中最前面的）
AbortPolicy 抛异常
CallerRunsPolicy 在当前线程同步执行
 */
public class RejectPolicyTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread());

        // 创建线程池。线程池的最大池大小和核心池大小都为1，"线程池"的阻塞队列容量为1。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue(1));

        // 设置线程池的拒绝策略
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            Runnable run = new MyRunnable("task" + i);
            pool.execute(run);
        }
        // 关闭线程池
        pool.shutdown();
    }


    private static class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.name + " is running by " + Thread.currentThread());
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}


