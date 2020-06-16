package com.duyj.work.concurrent.thread;

import com.duyj.work.utils.Q;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class TheadTest {

    public static void main(String[] args) throws InterruptedException {

        new SubThread().start();
        new Thread(new SubRunnable()).start();

        ExecutorService pool = Executors.newFixedThreadPool(1, new MyThreadFactory());
        pool.execute(new SubThread());
        pool.submit(new SubRunnable());
        pool.execute(() -> System.out.println("a" + 1 / 0));//捕获异常
        pool.submit(() -> System.out.println("a" + 1 / 0));//不捕获异常

        System.out.println("main thread end");
        pool.shutdown();
    }

    static class SubThread extends Thread {
        private AtomicLong id = new AtomicLong(0);

        @Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
                System.out.println(this.getClass().getSimpleName() + id.incrementAndGet() + " end");
            } catch (InterruptedException e) {
			}
		}
	}

	static class SubRunnable implements Runnable{
		@Override
		public void run() {
			System.out.println(this.getClass().getSimpleName()+ " end");
		}
	}

    private static class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Q.p(t.getClass().getSimpleName() + " exception catched:" + e.toString());
        }
    }

    private static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(new MyExceptionHandler());
            return t;
        }
    }

}


