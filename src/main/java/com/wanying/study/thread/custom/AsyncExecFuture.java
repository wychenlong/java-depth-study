package com.wanying.study.thread.custom;

import com.google.common.base.Objects;
import com.wanying.study.exception.TaskBusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by wychenlong on 2016/8/21.
 */
public class AsyncExecFuture<V> extends FutureTask<V> {

    protected static final Logger logger = LoggerFactory.getLogger(AsyncExecFuture.class);

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
        logger.info("costTime with: {}", costTime);
    }



    @Override
    public void run() {
        startTime = System.currentTimeMillis();
        if (Objects.equal(ownerCallable.getTaskName(), "task1")) {
            try {
                Thread.sleep(50000l);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            //调用了cancel后，检测中断状态
            if(isCancelled()){
                logger.info("检测到中断状态,task={}",ownerCallable.getTaskName());
                return;
            }
            logger.info("cancel无效果，任务继续运行,task={}",ownerCallable.getTaskName());
        }

    }


    public V getCustom(long timeout, TimeUnit unit) throws TaskBusinessException {
        try {
            return super.get(timeout, unit);
        }catch (Exception e) {
            throw new TaskBusinessException(ownerCallable.getTaskName(), e.getMessage(), e);
        }
    }
}
