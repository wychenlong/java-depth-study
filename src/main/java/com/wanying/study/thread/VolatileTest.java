package com.wanying.study.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/11/3.
 */
public class VolatileTest {
    /**
     * volatile语义相当于监视器锁，读相当于上锁，写相当于释放锁
     * 写先于发生读
     */
    private volatile boolean flag = false;
    int a  =0;
    private  boolean read(){
       if(flag){
           int i = a;
           System.out.println(i);
       }
        return flag;
    }

    private void write(){
        a=1;
        flag = true;
        System.out.println("write "+a);
    }
    public static void main(String args[]){
        final VolatileTest test = new VolatileTest();
        Executor executorWrite = Executors.newFixedThreadPool(2);
        executorWrite.execute(new Runnable() {
            @Override
            public void run() {
                test.write();
            }
        });
        Executor executorRead = Executors.newSingleThreadExecutor();
        executorRead.execute(new Runnable() {
            @Override
            public void run() {
                test.read();
            }
        });

    }

}
