package com.wanying.study.jvm.memeory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by wychenlong on 2017/1/17.
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOom {
    private static final int _1mb = 1024*1024;


    private static void unsafeDirectMemory() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unfafe = (Unsafe) unsafeField.get(null);
        while (true){
            unfafe.allocateMemory(_1mb);
        }
    }
    public static void main(String args[]){
        try {
            unsafeDirectMemory();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
