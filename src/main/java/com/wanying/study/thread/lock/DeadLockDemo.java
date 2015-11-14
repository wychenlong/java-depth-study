package com.wanying.study.thread.lock;

/**
 * Created by Administrator on 2015/11/14.
 * 死锁案例
 */
public class DeadLockDemo {
    private static String A = "a";
    private static String B = "b";

    public static void main(String args[]){
        DeadLockDemo deadLockDemo = new DeadLockDemo();
        Thread t1 = new Thread(deadLockDemo.new RunnableA());
        Thread t2 = new Thread(deadLockDemo.new RunnableB());
        t1.start();
        t2.start();
    }
    public  class RunnableA implements Runnable{
        @Override
        public void run() {
            synchronized (A){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(B){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
    }

    private class RunnableB implements Runnable{
        @Override
        public void run() {
            synchronized (B){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(A){
                    System.out.println(Thread.currentThread().getName());
                }
            }
        }
    }
}
