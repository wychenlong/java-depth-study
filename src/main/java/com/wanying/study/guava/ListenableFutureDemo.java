package com.wanying.study.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.wanying.study.exception.TaskBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wychenlong on 2016/8/18.
 */
public class ListenableFutureDemo {
    protected static final Logger logger = LoggerFactory.getLogger(ListenableFutureDemo.class);
    public static void main(String args[]) {

        ListenableFutureDemo listenableFutureDemo = new ListenableFutureDemo();
        ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));
        AsyncExecCallable asyncExecCallable1 = listenableFutureDemo.new AsyncExecCallable(1,"test1");
        final ListenableFuture<String> future = executor.submit(asyncExecCallable1);
        final ListenableFuture<String> future2 = executor.submit(listenableFutureDemo.new AsyncExecCallable(21,"test2"));

        jdkFutureTest(future, future2);
        logger.info("回调展示");
        callBackFuture(future, future2,listenableFutureDemo);

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
            logger.info("无回调"+Arrays.toString(futureAll.get().toArray()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }catch (Exception e){
            if(e instanceof TaskBusinessException){
                logger.error("task error",e);
            }
        }
        logger.info("exit..");
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
        List<ListenableFuture<String>> futuresList = Lists.newArrayList();
        futuresList.add(future2);
        futuresList.add(future);
        Futures.addCallback(future2, listenableFutureDemo.new FutureCallbackTest());
        Futures.addCallback(future, listenableFutureDemo.new FutureCallbackTest());

        ListenableFuture<List<String>> successLsit =  Futures.successfulAsList();


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
            logger.info(result.getClass().getName());
            logger.info("success with: {}", result);
        }

        public void onFailure(Throwable thrown) {
            if (thrown instanceof TaskBusinessException) {
                TaskBusinessException taskBusinessException = (TaskBusinessException) thrown;
                logger.info("onFailure  thread code {}", taskBusinessException.getCode());
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

        public int getVersion(){
            return this.version;
        }
    }

    /**
     * future实现
     */
    private class AsyncExecFuture extends FutureTask<String>{

        private long startTime;
        private long endTime;
        private AsyncExecCallable ownerCallable;
        public AsyncExecFuture(AsyncExecCallable callable) {
            super(callable);
            ownerCallable = callable;
        }

        @Override
        protected void done() {
            endTime = System.currentTimeMillis(); // 记录一下时间点，Future在cancel调用，正常完成，或者运行出异常都会回调该方法
            long costTime = endTime -startTime;
            logger.info("costTime with: {}", costTime);
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


        public String getCustom(long timeout, TimeUnit unit)
                throws TaskBusinessException {
            try{
                return super.get(timeout,unit);
            }catch (Exception e){
                throw   new TaskBusinessException(String.valueOf(ownerCallable.getVersion()),e.getMessage(),e.getCause());
            }


        }
    }
}
