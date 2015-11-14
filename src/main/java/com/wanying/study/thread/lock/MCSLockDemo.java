package com.wanying.study.thread.lock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Administrator on 2015/11/14.
 * msc lock 基于链表的公平自旋锁，与CLH不同的是真实存在的物理结构，直接前驱节点负责通知后驱节点结束自旋
 * 从而极大地减少了不必要的处理器缓存同步的次数，降低了总线和内存的开销。
 * msc自旋自己的节点属性
 * t1                                              t2
 * Node1--->null--->Node1                          Node2--->Node1--->Node2
 * step4发生与t2 step2之前造就了step5再次检查      Node1.next=Node2
 *
 *
 */
public class MCSLockDemo {
    //最后一个申请锁对象节点
    private  volatile QNode queue;
    //存储线程节点对象
    private static final ThreadLocal<QNode> LOCAL = new ThreadLocal<QNode>();
    //原子更新器
    private AtomicReferenceFieldUpdater<MCSLockDemo,QNode> UPDATER = AtomicReferenceFieldUpdater.newUpdater(MCSLockDemo.class,QNode.class,"queue");

    public void lock(){
        QNode qNode = new QNode();
        LOCAL.set(qNode);
        //得到前置节点
        QNode preNode = UPDATER.getAndSet(this,qNode);//step1
        if(preNode != null){
            //前置节点后驱节点为当前节点
            preNode.next = qNode; //step2
            while(qNode.locked){ //step3
                //System.out.println("wait lock\t" + Thread.currentThread().getName());
            }
            System.out.println("while after lock\t" + Thread.currentThread().getName());
        }else{
            System.out.println("first get  lock\t" + Thread.currentThread().getName());
        }
    }

    public void unLock(){
        QNode qNode = LOCAL.get();
        //检查是否有人排队在自己后面
        if(qNode.next == null){//step4
            //二次检查确认有没有节点排在自己后面
            if(UPDATER.compareAndSet(this,qNode,null)){//step5

            }else{
                // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
                while (qNode.next == null){//step6
                    //System.out.println("wait current node next null\t" + Thread.currentThread().getName());
                }
                qNode.next.locked = false;
                qNode.next = null;
            }
        }else{
            qNode.next.locked = false;
            qNode.next = null;
        }
    }


    public static  void main(String args[]){
        int size = 12;
        final MCSLockDemo mcsLockDemo = new MCSLockDemo();
        Executor executor = Executors.newFixedThreadPool(size);
        for(int i = 0 ; i < size ; i++){
            executor.execute(new MyRunnable(mcsLockDemo));
        }
    }

    private static class QNode{
        private volatile QNode next;
        private volatile boolean locked = true;
    }


    private static class MyRunnable implements Runnable{
        private MCSLockDemo mcsLockDemo;
        public MyRunnable(MCSLockDemo mcsLockDemo){
            this.mcsLockDemo = mcsLockDemo;
        }
        @Override
        public void run() {
            mcsLockDemo.lock();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mcsLockDemo.unLock();
        }
    }
}
