package com.thread.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by XUGY on 2017-07-22.
 */
public class ThreadPool {
    // �����̳߳ض�������֧��ҵ��
//    private static ThreadPoolExecutor poolExecutor = null;
	private volatile static ThreadPool instance;
	private ThreadPool(){}
    /**
     * @Description ��ȡ�̳߳ض���ͨ��˫�ؼ����ʵ�֣���
     * @param
     * @return ThreadPoolExecutor �̳߳ض���
     * @exception @author
     *                zhehong.qiu email:qiuzhehong@tzx.com.cn
     * @version 1.0 2017-06-19
     * @see
     */
    /*public static ThreadPoolExecutor getThreadPool() {
        if(poolExecutor == null){
            synchronized (ThreadPool.class) {
                if(poolExecutor == null){
					
					 * ���������߳���Ϊ20������߳���Ϊ50(�����������ʱ�Ż����Ӵ��ں����߳�����С������߳������߳�)���̳߳أ�ʹ��ArrayBlockingQueue�������У����д�СΪ1000��
					 * �߳����������д�Сʱ�Ĳ���Ϊ���ԣ��ɵ����̳߳ص����߳��Լ���ִ�����񣩡�
					 
                    poolExecutor = new ThreadPoolExecutor(20,50,3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        return poolExecutor;
    }*/
	
	private static ThreadPool getInstance() {
		  if (instance == null) {
			synchronized (ThreadPool.class) {
				if (instance == null) {
					instance = new ThreadPool();
				}
			}
		  }
		return instance;
	  }
	
	private ThreadPoolExecutor getPool() {
    	return TaskThreadPoolExecutor.getThreadPool(this.getClass().getName(), 
				20,50,3, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
	
    public static ThreadPoolExecutor getThreadPool() {
    	return ThreadPool.getInstance().getPool();
    }
}
