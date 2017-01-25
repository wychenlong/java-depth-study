package com.wanying.study.jvm.memeory;

/**
 * Created by wychenlong on 2016/12/14.
 * 主要是用于类文件查看
 */
public class ClassFileViewTest {

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
        ClassFileViewTest memoryTest = new ClassFileViewTest();
        memoryTest.test();
    }
}
