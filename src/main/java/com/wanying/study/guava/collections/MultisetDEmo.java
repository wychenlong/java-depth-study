package com.wanying.study.guava.collections;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by cl on 2016/7/31.
 */
public class MultisetDemo {
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
        System.out.println("map统计A出现的次数："+ counts.get("A"));
        Multiset<String> myMultiset = HashMultiset.create();
        myMultiset.addAll(words);
        System.out.println("Guava统计A出现的次数："+ myMultiset.count("A"));
    }

    /**
     * Multiset api操作
     */
    private static void testMultiset(){
        Multiset<String> myMultiset = HashMultiset.create();
        myMultiset.addAll(words);
        System.out.println("元素大小："+ myMultiset.size());
        System.out.println("A出现的次数："+ myMultiset.count("A"));
        //循环统计信息
        Set<Multiset.Entry<String>> countSetEntry = myMultiset.entrySet();
        for( Multiset.Entry<String> entry : countSetEntry){
            System.out.println("原始数据："+ entry.getElement() + "，出现的次数："+entry.getCount());
        }

        //循环元素
        Set<String> elementSet = myMultiset.elementSet();
        for(String element : elementSet){
            System.out.println("元素："+ element);
        }
        myMultiset.setCount("A",4);
        System.out.println("修改A出现的次数："+ myMultiset.count("A"));
    }


}
