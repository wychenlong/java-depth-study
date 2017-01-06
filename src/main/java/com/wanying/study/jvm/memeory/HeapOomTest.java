package com.wanying.study.jvm.memeory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wychenlong on 2016/12/15.
 * vm param
 * -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOomTest {
    static class OomObject{

    }

    public static void main(String args[]){
        System.out.println(Runtime.getRuntime().maxMemory());
        List<OomObject> oomObjectList = new ArrayList<OomObject>();
        while(true){
            oomObjectList.add(new OomObject());
        }
    }
}
