package com.wanying.study.thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.wanying.study.exception.TaskBusinessException;
import com.wanying.study.guava.vo.Person;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wychenlong on 2016/8/18.
 */
public class ListenableFutureDemo {
    public static void main(String args[]) {
        ListenableFutureDemo listenableFutureDemo = new ListenableFutureDemo();
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        AsyncExecCallable asyncExecCallable1 = listenableFutureDemo.new AsyncExecCallable(1,"test1");
        final ListenableFuture<String> future = executor.submit(asyncExecCallable1);
        final ListenableFuture<String> future2 = executor.submit(listenableFutureDemo.new AsyncExecCallable(21,"test2"));

        ListenableFuture<?> g=  executor.submit(listenableFutureDemo.new AsyncExecFuture(asyncExecCallable1));
        Futures.addCallback(g, listenableFutureDemo.new FutureCallbackTest());
       // jdkFutureTest(future, future2);
       // System.out.println("回调展示");
        //callBackFuture(future, future2,listenableFutureDemo);

    }




    /**
     * jdk方式异步无回调，多视图控制
     * @param future
     * @param future2
     */
    private static void jdkFutureTest(ListenableFuture<String> future, ListenableFuture<String> future2) {
        List<ListenableFuture<String>> futures = Lists.newArrayList();
        futures.add(future);
        futures.add(future2);
        ListenableFuture<List<String>> futureAll = Futures.successfulAsList(futures);
        // Futures.allAsList(futures) 一个失败全部失败或者取消
        try {
            System.out.println("无回调"+Arrays.toString(futureAll.get().toArray()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){
            if(e instanceof TaskBusinessException){
                e.printStackTrace();
            }
        }
        System.out.println("exit..");
    }

    /**
     * 异步回调处理
     * @param future
     * @param future2
     * @param listenableFutureDemo
     */
    private static void callBackFuture(ListenableFuture<String> future, ListenableFuture<String> future2, ListenableFutureDemo listenableFutureDemo ){
        //有个失败全局失败
       // final ListenableFuture allFutures = Futures.allAsList(future, future2);
        Futures.addCallback(future, listenableFutureDemo.new FutureCallbackTest());
        Futures.addCallback(future2, listenableFutureDemo.new FutureCallbackTest());
    }

    /**
     * 带监控时间的回调
     */
    private static void monitorCallBackFuture(){

    }

    /**
     * 回调函数类实现
     */
    private class FutureCallbackTest implements FutureCallback{

        public void onSuccess(Object result) {
            System.out.println(result.getClass());
            System.out.printf("success with: %s%n", result);
        }

        public void onFailure(Throwable thrown) {
            if (thrown instanceof TaskBusinessException) {
                TaskBusinessException taskBusinessException = (TaskBusinessException) thrown;
                System.out.printf("onFailure  thread code= %s%n ", taskBusinessException.getCode());
            }

        }
    }

    /**
     * callable接口实现
     */
    private class AsyncExecCallable implements Callable<String> {
        private int version;
        private String taskName;

        public AsyncExecCallable(int version, String taskName) {
            this.version = version;
            this.taskName = taskName;
        }

        @Override
        public String call() throws Exception {
            if (version == 1) {
                throw new TaskBusinessException(taskName,"测试异常",new Exception());
            }
            return "SUCCESS";
        }
    }

    /**
     * future实现
     */
    private class AsyncExecFuture extends FutureTask<String>{

        private long startTime;
        private long endTime;
        public AsyncExecFuture(Callable<String> callable) {
            super(callable);
        }

        @Override
        protected void done() {
            endTime = System.currentTimeMillis(); // 记录一下时间点，Future在cancel调用，正常完成，或者运行出异常都会回调该方法
            long costTime = endTime -startTime;
            System.out.printf("costTime with: %s%n", costTime);
        }

        @Override
        public void run() {
            startTime = System.currentTimeMillis();
            try {
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.run();
        }
    }
}
