package com.wanying.study.guava.collections;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.wanying.study.guava.ListenableFutureDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by cl on 2016/7/31.
 */
public class ImmutableDemo {
    private static final Logger logger = LoggerFactory.getLogger(ImmutableDemo.class);


    ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "baz");
    public  static void main(String args[]){

        testCopy();

    }

    private static void testCopy(){
        List<String> dataList = Lists.newArrayList();
        dataList.add("d");
        dataList.add("a");
        dataList.add("b");
        //从继承体系来看，已经成为JDK标准类
        ImmutableSet copyofSet =  ImmutableSet.copyOf(dataList);
        //jdk不可变对象
        Collection jdkUnmodif=   Collections.unmodifiableCollection(dataList);
        //原始集合修改
        dataList.add(0,"e");
        logger.info("不可变对象生成，原始集合添加元素，JDK表现：{}",jdkUnmodif.contains("e"));
        logger.info("不可变对象生成，原始集合添加元素，guava表现：{}",copyofSet.contains("e"));

    }
}
