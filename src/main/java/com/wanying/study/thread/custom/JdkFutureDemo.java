package com.wanying.study.thread.custom;

import com.google.common.collect.Lists;
import com.wanying.study.exception.TaskBusinessException;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wychenlong on 2016/8/21.
 */
public class JdkFutureDemo {
    public static void main(String args[]){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        AsyncExecCallable<String> asyncExecCallable = new AssetCallableImpl("task1","1");
        AsyncExecFuture<String> futureTask = new AsyncExecFuture(asyncExecCallable);
        executorService.submit(futureTask);

        AsyncExecCallable<String> asyncExecCallable1 = new AssetCallableImpl("task2","2");
        AsyncExecFuture<String> futureTask1 = new AsyncExecFuture(asyncExecCallable1);
        executorService.submit(futureTask1);

        List<AsyncExecFuture<String>> asyncExecFutureList = Lists.newArrayList();
        asyncExecFutureList.add(futureTask);
        asyncExecFutureList.add(futureTask1);
        for(AsyncExecFuture<String> future : asyncExecFutureList){
            try {
                String result = future.getCustom(2, TimeUnit.SECONDS);
            } catch (TaskBusinessException e) {
                e.printStackTrace();
                future.cancel(true);
                System.out.println("任务异常码："+e.getCode()+"；异常类型："+e.getMessage());
            }

//            try {
//                future.get(2, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                future.cancel(true);
//                e.printStackTrace();
//            }
        }
        executorService.shutdown();
    }


}
