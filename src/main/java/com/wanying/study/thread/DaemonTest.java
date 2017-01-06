package com.wanying.study.thread;

/**
 * Created by wychenlong on 2016/11/9.
 */
public class DaemonTest {
    public static void main(String args[]){

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("守护线程");
                }
            }
        });
        t.setDaemon(true);
        t.start();
        System.out.println("主线程退出");//虚拟机退出
    }
}
