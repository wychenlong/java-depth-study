package com.wanying.study.jvm.memeory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wychenlong on 2017/1/13.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String args[]){
        String str1 = new StringBuilder("计算机").append("软件").toString();
        //str1.intern() jdk版本不同实现不同
        System.out.println(str1.intern()==str1);
        int i = 0 ;
        List<String> list = new ArrayList<String>();
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }
}
