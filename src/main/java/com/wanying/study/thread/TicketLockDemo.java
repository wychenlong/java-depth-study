package com.wanying.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2015/11/11.
 * 票据锁实现，主要利用服务器票据号和本地票据号进行对比
 */
public class TicketLockDemo {
    private AtomicInteger ticketNumber = new AtomicInteger();
    private AtomicInteger serverTicket = new AtomicInteger();
    private static final ThreadLocal<Integer> LOCL = new ThreadLocal<Integer>();

    public void lock(){
        int mytiket = ticketNumber.getAndIncrement();
        LOCL.set(mytiket);
        while(mytiket == serverTicket.get()){
            System.out.println("lock number "+mytiket);
        }
    }

    public void unlock(){
        int mytiket = ticketNumber.getAndIncrement();
        serverTicket.compareAndSet(mytiket,mytiket+1);
    }

    public static  void main(String args[]){
        final TicketLockDemo ticketLockDemo = new TicketLockDemo();

    }
}
