package com.wanying.study.jvm.memeory;

/**
 * Created by wychenlong on 2016/12/14.
 */
public class MemoryTest {

    private final int SIZE =10;
    private int max = 20;
    private String test = "test";

    public void test(){
        int x = 10;
        int y = 12;
        int z =14;
        int t = x+y+z;
        System.out.println(t);
    }

    public void test2(int x){
//        while(true){
//            test();
//        }
        test();
    }

    public static void main(String args[]){
        MemoryTest memoryTest = new MemoryTest();
        memoryTest.test();
    }
}
