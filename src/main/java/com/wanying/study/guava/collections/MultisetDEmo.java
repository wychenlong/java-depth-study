package com.wanying.study.guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.wanying.study.guava.basic.ObjectDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cl on 2016/7/31.
 */
public class MultisetDemo {
    private static final Logger logger = LoggerFactory.getLogger(MultisetDemo.class);

    private static List<String> words = Lists.newArrayList("A","B","C","A","A","C") ;

    public static  void main(String args[]){
        testCount();
        testMultiset();
    }

    /**
     * 统计单词出现次数
     */
    private static void testCount(){
        //传统统计
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
        logger.info("map统计A出现的次数：{}", counts.get("A"));
        Multiset<String> myMultiset = HashMultiset.create();
        myMultiset.addAll(words);
        logger.info("Guava统计A出现的次数：{}", myMultiset.count("A"));
    }

    /**
     * Multiset api操作
     */
    private static void testMultiset(){
        Multiset<String> myMultiset = HashMultiset.create();
        myMultiset.addAll(words);
        logger.info("元素大小：{]", myMultiset.size());
        logger.info("A出现的次数：{}", myMultiset.count("A"));
        //循环统计信息
        Set<Multiset.Entry<String>> countSetEntry = myMultiset.entrySet();
        for( Multiset.Entry<String> entry : countSetEntry){
            logger.info("原始数据：{} ，出现的次数：{}",entry.getElement() ,entry.getCount());
        }

        //循环元素
        Set<String> elementSet = myMultiset.elementSet();
        for(String element : elementSet){
            logger.info("元素：{}",element);
        }
        myMultiset.setCount("A",4);
        logger.info("修改A出现的次数：{}",myMultiset.count("A"));
    }


}
