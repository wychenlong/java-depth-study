package com.wanying.study.thread.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2015/11/11.
 * 票据锁实现了公平竞争，原理类似银行排队。
 * 首先生成本地票据号，然后获取服务端票据号（代表拥有锁的线程的票据号）进行对比若不相等则自旋进行等待
 */
public class TicketLockDemo {
    private AtomicInteger ticketNumber = new AtomicInteger();
    private AtomicInteger serverTicket = new AtomicInteger();
    //存储当前线程获取的版本号
    private static final ThreadLocal<Integer> LOCL = new ThreadLocal<Integer>();

    public void lock(){
        int mytiket = ticketNumber.getAndIncrement();
        LOCL.set(mytiket);
        //没有获取锁就不断轮询
        while(mytiket != serverTicket.get()){
            System.out.println("lock number "+mytiket);
        }
    }

    public void unLock(){
        int mytiket = LOCL.get();
        serverTicket.compareAndSet(mytiket,mytiket+1);
    }

    public static  void main(String args[]){
        int size = 3;
        final TicketLockDemo ticketLockDemo = new TicketLockDemo();
        Executor executor = Executors.newFixedThreadPool(size);
        for(int i = 0 ; i < size ; i++){
            executor.execute(new MyRunnable(ticketLockDemo));
        }
    }

    private static class MyRunnable implements Runnable{
        private TicketLockDemo ticketLockDemo;
        public MyRunnable(TicketLockDemo ticketLockDemo){
           this.ticketLockDemo = ticketLockDemo;
        }
        @Override
        public void run() {
            ticketLockDemo.lock();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketLockDemo.unLock();
        }
    }
}
