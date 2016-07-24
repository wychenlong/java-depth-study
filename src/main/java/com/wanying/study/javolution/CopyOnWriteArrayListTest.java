package com.wanying.study.javolution;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/11/4.
 * 测试数据一致性问题
 */
public class CopyOnWriteArrayListTest {
    public static void main(String args[]){
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(11);
        copyOnWriteArrayList.add(12);
        copyOnWriteArrayList.add(13);
        copyOnWriteArrayList.add(14);

        Executor executorRemove = Executors.newScheduledThreadPool(2);
        int x = 0;
        for(int i = 0 ;i < 2; i++){
            x = i;
            final int finalX = x;
            Runnable a = new Runnable() {
                @Override
                public void run() {
                    System.out.println("remove");
                    copyOnWriteArrayList.remove(finalX);
                }
            };
            executorRemove.execute(a);
        }
        Executor executorAdd = Executors.newSingleThreadExecutor();
        executorAdd.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("add");
                copyOnWriteArrayList.add(16);
            }
        });
        try {
            Thread.sleep(5000l);
            System.out.println(copyOnWriteArrayList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
