package com.wanying.study.thread;

import com.google.common.base.Objects;
import com.wanying.study.exception.TaskBusinessException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by wychenlong on 2016/8/21.
 */
public class AsyncExecFuture<V> extends FutureTask<V> {

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
        long costTime = endTime - startTime;
        System.out.printf("costTime with: %s%n", costTime);
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        if (Objects.equal(ownerCallable.getTaskName(), "task1")) {
            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.run();
    }


    public V getCustom(long timeout, TimeUnit unit) throws TaskBusinessException {
        try {
            return super.get(timeout, unit);
        }catch (Exception e) {
            throw new TaskBusinessException(ownerCallable.getTaskName(), e.getMessage(), e);
        }
    }
}
