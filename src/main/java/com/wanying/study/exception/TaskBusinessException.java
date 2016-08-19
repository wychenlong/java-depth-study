package com.wanying.study.exception;

/**
 * Created by wychenlong on 2016/8/18.
 */
public class TaskBusinessException extends Exception {
    private String code;

    public TaskBusinessException (String code,String message,Throwable cause){
        super(message,cause);
        this.code = code;
    }


    public String getCode(){
        return code;
    }
}
