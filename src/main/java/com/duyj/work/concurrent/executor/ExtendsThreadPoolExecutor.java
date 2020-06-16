package com.duyj.work.concurrent.executor;

import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * 扩展ThreadPoolExecutor
 */
public class ExtendsThreadPoolExecutor {

    public static void main(String[] args) {
        MyThreadPoolExecutor.newFixedThreadPoolExecutor(10);

        Executor exe = new ThreadPoolExecutor(2, 2, 200, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(), new MyThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    //自定义线程工厂
    private static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setPriority(5);
            t.setName("my thread");
            return t;
        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public LinkedList<Runnable> running = new LinkedList<Runnable>();

        private MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
            running.add(r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
            running.remove(r);
        }

        public static MyThreadPoolExecutor newFixedThreadPoolExecutor(int n) {
            return new MyThreadPoolExecutor(n, n, 500, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        }

        @Override
        protected void terminated() {
            System.out.println("pool terminated");
        }
    }

}
