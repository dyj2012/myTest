package com.duyj2.work.patterns.singleton;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;

class SingletonTest implements Runnable {

    //倒计锁
	private static CountDownLatch latch = new CountDownLatch(1);

    //线程安全的Set
	static Set<String> set = new ConcurrentSkipListSet<String>();

	@Override
	public void run() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		set.add(SingletonClass2.getInstance().toString());
	}

	public static void main(String[] args) throws Exception {

		for (int k = 0; k < 2000; k++) {
			SingletonTest tt = new SingletonTest();
			Thread t = new Thread(tt);
			t.start();
		}

		//释放锁
		latch.countDown();

		Thread.sleep(2000);

		System.out.println(set.size());
	}
}