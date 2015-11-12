package com.wanying.study.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2015/11/11.
 * 自旋锁实现测试，非公平的自旋锁
 */
public class SpinLockDemo {
    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock(){
        Thread currentThread = Thread.currentThread();
        while(owner.compareAndSet(null,currentThread)){
            System.out.println("lock name "+currentThread.getName());
        }
    }

    public void unlock(){
        Thread currentThread = Thread.currentThread();
        System.out.println("unlock name "+currentThread.getName());
        owner.compareAndSet(currentThread,null);
    }

    public static void main(String args[]){
        final SpinLockDemo lockDemo = new SpinLockDemo();
        Executor executor = Executors.newFixedThreadPool(3);
        for(int i = 0 ; i < 3;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    lockDemo.lock();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lockDemo.unlock();

                }
            });
        }

    }
}
