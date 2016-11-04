package com.wanying.study.thread.custom;

/**
 * Created by wychenlong on 2016/10/27.
 */
public class ThreadInterruptTest {
    public static void main(String args[]){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(Thread.currentThread().isInterrupted()){
                    System.out.println("线程中断");
                }
                while (true){
                    System.out.println("线程中断没有停止");
                }
            }
        });
        //线程没有启动就设置取消
        //t.interrupt();
        t.start();
        //打破休眠收到中断状态
        t.interrupt();
        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
