package com.wanying.study.thread;


import java.util.concurrent.Callable;

/**
 * Created by wychenlong on 2016/8/21.
 */
public abstract class AsyncExecCallable<V> implements Callable {
    public abstract String getTaskName();
}
