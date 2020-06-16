package com.duyj.work.concurrent.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	public static void main(String[] args) throws Exception {

		final CountDownLatch latch = new CountDownLatch(5);

		for (int k = 0; k < 5; k++) {
			final int n = k;
			new Thread(new Runnable() {

				private int id = n;

				@Override
				public void run() {
					try {
						System.out.println("thread " + id + " begin.");
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
					}
					System.out.println("thread " + id + " run over.");
					latch.countDown();
				}
			}).start();
		}

		latch.await();
		System.out.println("main end");
	}

	public static void main1(String[] args) throws Exception {

		final CountDownLatch latch = new CountDownLatch(1);

		for (int k = 0; k < 5; k++) {
			final int n = k;
			new Thread(new Runnable() {

				private int id = n;

				@Override
				public void run() {
					try {
						System.out.println("thread " + id + " begin.");
						latch.await();
					} catch (InterruptedException e) {
					}
					System.out.println("thread " + id + " run over.");
					latch.countDown();
				}
			}).start();
		}

		latch.countDown();
		System.out.println("main end");
	}
}
