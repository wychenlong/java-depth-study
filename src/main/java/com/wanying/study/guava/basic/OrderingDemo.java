package com.wanying.study.guava.basic;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by cl on 2016/7/9.
 * 排序测试
 */
public class OrderingDemo {
    public static void main(String args[]){
        List<String> strList = Arrays.asList("zsbc", "a", "bcd","yz","q4","zabc");
        Comparator natural = Ordering.natural();
        //自然排序
        Collections.sort(strList,natural);
        System.out.println(strList);
        //倒排
        Comparator reverse = Ordering.natural().reverse();
        Collections.sort(strList,reverse);
        System.out.println(strList);

        //求最小元素、最大元素
        System.out.println();

    }
}
