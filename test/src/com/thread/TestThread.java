/**
 * ���߳�ѭ��10�Σ��������߳�ѭ��100�Σ������ֻص����߳�ѭ��10�Σ������ٻص����߳�ѭ��100�Σ����ѭ��50�Ρ�
 */
package com.thread;

public class TestThread {
    
    public static void main(String[] args) {
        new TestThread().init();
    }
    
    public void init() {
       final Business business = new Business();
       
       new Thread(new Runnable() {
        public void run() {
            for (int i=0; i<10; i++) {
                business.subThread(i);
            }
        }
       }).start();
       
       for (int i=0; i<10; i++) {
        business.mainThread(i);
       }
    }
    
    private class Business {
        boolean bShouldSub = true; //�����൱�ڶ�����һ������˭ִ�е�һ���ź�
        
        public synchronized void mainThread(int i) {
            if (bShouldSub) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }   
            for (int j=0; j<5; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            
            bShouldSub = true;
            this.notify();
            
        }
        
        public synchronized void subThread(int i) {
            if (!bShouldSub) {
                try {
                    this.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }    
            for (int j=0; j<10; j++) {
                System.out.println(Thread.currentThread().getName() + ":i=" + i + ",j=" + j);
            }
            
            bShouldSub = false;
            this.notify();
            
        }
    }
}
