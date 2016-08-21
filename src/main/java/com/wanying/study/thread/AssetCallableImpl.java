package com.wanying.study.thread;

/**
 * Created by wychenlong on 2016/8/21.
 */
public class AssetCallableImpl extends AsyncExecCallable<String> {
    private String task;
    private String data;
    public AssetCallableImpl(String taskName,String data){
        this.task  = taskName;
        this.data = data;
    }

    @Override
    public String getTaskName() {
        return task;
    }

    @Override
    public Object call() throws Exception {
        return data;
    }
}
