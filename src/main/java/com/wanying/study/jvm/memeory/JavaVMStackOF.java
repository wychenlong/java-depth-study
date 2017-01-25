package com.wanying.study.jvm.memeory;

/**
 * Created by wychenlong on 2017/1/12.
 */
public class JavaVMStackOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String args[]) throws Throwable {
        JavaVMStackOF javaVMStackOF = new JavaVMStackOF();
        try {
            javaVMStackOF.stackLeak();
        }catch (Throwable e){
            System.out.print("stack length :"+javaVMStackOF.stackLength);
            throw e;
        }
    }
}
