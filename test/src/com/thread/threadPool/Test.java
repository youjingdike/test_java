package com.thread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test implements Runnable {
	public void tst() {
		ThreadPoolExecutor executor = TaskThreadPoolExecutor.getThreadPool(this.getClass().getName(),
                5, 50, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(200),
                new ThreadPoolExecutor.CallerRunsPolicy() );
		System.out.println("executor:"+executor);
	}
	
	public void tst2() {
		/*ThreadPoolExecutor executor = TaskThreadPoolExecutor.getThreadPool(this.getClass().getName(),
                5, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(100),
                new ThreadPoolExecutor.AbortPolicy());
		try {
			executor.execute(a);
		} catch (RejectedExecutionException e) {
			System.out.println(Thread.currentThread()+":"+i+",报错了。。。");
		}*/
		ThreadPoolExecutor executor = TaskThreadPoolExecutor.getThreadPool(this.getClass().getName(),
				5, 5, 3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
				new ThreadPoolExecutor.CallerRunsPolicy() );
		
		System.out.println("executor:"+executor);
		for (int i=0;i<100000;i++ ){
			System.out.println(Thread.currentThread().getName()+"打印的i:"+i+",sttt");
			/*if (i%2==0) {
				TestRunnable a = new TestRunnable(i);
				try {
					executor.execute(a);
				} catch (RejectedExecutionException e) {
					System.out.println(Thread.currentThread()+":"+i+",报错了。。。");
				}
			} else {
				System.out.println(i+",不用线程池处理...");
			}*/
			TestRunnable a = new TestRunnable(i);
			executor.execute(a);
			System.out.println(Thread.currentThread().getName()+"打印的i:"+i+",eddd");
		}
		System.out.println("!!!!");
		executor.shutdown();
		System.out.println("@@@");
	}
	
	public void tst1() {
		ThreadPoolExecutor executor = ThreadPool.getThreadPool();
		System.out.println("executor:"+executor);
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.tst2();
		/*for (int i=0;i<50;i++) {
			new Thread(test).start();
		}*/
	}
	
	@Override
	public void run() {
		tst();
		tst1();
	}
}
