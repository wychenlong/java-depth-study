package com.wanying.study.thread.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Administrator on 2015/11/12.
 * 是一种基于链表的可扩展、高性能、公平的自旋锁，申请线程只在本地变量上自旋，它不断轮询前驱的状态，如果发现前驱释放了锁就结束自旋。
 * 从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。
 * 没有物理的链表结构，在前置节点的属性进行自旋.
 * 实现原理如下：
 * 当一个线程需要获取锁的时候创建一个新的节点，locked设置为true代表了需要获取一个锁
 * 然后在尾部线程调用getAdSet方法，是自己成为队列尾部与此同时得到前继节点myPred的引用，
 * 线程在前继节点的locked属性开始自旋直到前继节点释放锁，当线程需要释放锁的时候把当前节点
 * 的locked属性设置为false.
 *
 */
public class CLHLockDemo {

    //尾部节点
    private volatile QNode tail ;
    //存储当前线程节点信息
    private static final ThreadLocal<QNode> LOCAL = new ThreadLocal<QNode>();
    //tail原子更新器
    private static final AtomicReferenceFieldUpdater<CLHLockDemo,QNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(CLHLockDemo.class,QNode.class,"tail");

    /**
     *获取锁
     */
    public void lock(){
        //为当前线程创建一个节点
        QNode currentNode = new QNode(Thread.currentThread().getName());
        LOCAL.set(currentNode);
        //更新tail值并返回旧值,入队出队
        QNode myPred = UPDATER.getAndSet(this,currentNode);
        if(myPred != null){
            System.out.println("pred node---" + myPred.getName());
            //是否需要自旋等待获取锁
            while (myPred.locked){
                System.out.println("自旋线程---" + currentNode.getName());
            }
            System.out.println(" while get lock---" + currentNode.getName());
        }else{
            System.out.println("get lock---" + currentNode.getName());
        }
    }

    /**
     *释放锁
     */
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

    private static class QNode{
        private String name;
        public QNode(String name){
            this.name = name;
        }
        //是否需要等待
        private volatile boolean locked = true;

        public String getName(){
            return name;
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
