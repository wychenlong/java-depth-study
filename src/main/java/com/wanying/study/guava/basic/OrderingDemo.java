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
        System.out.println("自然排序："+strList);
        //倒排
        Comparator reverse = Ordering.natural().reverse();
        Collections.sort(strList,reverse);
        System.out.println("自然倒排："+strList);

        List<Integer> numberList = Arrays.asList(6,61,28,12,45);
        //求最小元素、最大元素
        System.out.println("最大："+Ordering.natural().max(numberList));
        System.out.println("最小："+Ordering.natural().min(numberList));

//      比较顺序
        System.out.println("比较顺序："+Ordering.explicit(numberList));



    }
}
