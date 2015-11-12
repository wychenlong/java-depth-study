package com.wanying.study.thread.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Administrator on 2015/11/12.
 * 是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
 * 从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。
 * 没有物理的链表结构，在前置节点的属性进行自旋.
 *
 */
public class CLHLockDemo {
    private static class QNode{
        //是否需要等待
        private volatile boolean locked = true;
    }

    //尾部节点
    private volatile QNode tail ;
    //存储当前线程节点信息
    private static final ThreadLocal<QNode> LOCAL = new ThreadLocal<QNode>();
    //tail原子更新器
    private static final AtomicReferenceFieldUpdater<CLHLockDemo,QNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLockDemo.class,QNode.class,"tail");

    public void lock(){
        //为当前线程创建一个节点
        QNode currentNode = new QNode();
        LOCAL.set(currentNode);
        //更新tail值并返回旧值
        QNode preNode = UPDATER.getAndSet(this,currentNode);
        if(preNode != null){
            //是否需要自旋等待获取锁
            while (preNode.locked){
                System.out.println("自旋线程---" + Thread.currentThread().getName());
            }
            System.out.println(" while get lock---" + Thread.currentThread().getName());
        }else{
            System.out.println("get lock---" + Thread.currentThread().getName());
        }
    }

    public void unLock(){
        QNode currentNode = LOCAL.get();
//        currentNode.locked = false;
        if(!UPDATER.compareAndSet(this,currentNode,null)){
            currentNode.locked = false;
        }
    }


    public static  void main(String args[]){
        int size = 3;
        final CLHLockDemo clhLockDemo = new CLHLockDemo();
        Executor executor = Executors.newFixedThreadPool(size);
        for(int i = 0 ; i < size ; i++){
            executor.execute(new MyRunnable(clhLockDemo));
        }
    }

    private static class MyRunnable implements Runnable{
        private CLHLockDemo clhLockDemo;
        public MyRunnable(CLHLockDemo clhLockDemo){
            this.clhLockDemo = clhLockDemo;
        }
        @Override
        public void run() {
            clhLockDemo.lock();

            clhLockDemo.unLock();
        }
    }
}
