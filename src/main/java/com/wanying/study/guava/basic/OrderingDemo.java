package com.wanying.study.guava.basic;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.wanying.study.guava.vo.Foo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by cl on 2016/7/9.
 * 排序测试
 */
public class OrderingDemo {
    private static final Logger logger = LoggerFactory.getLogger(OrderingDemo.class);
    public static void main(String args[]){
        List<String> strList = Arrays.asList("zsbc", "a", "bcd","yz","q4","zabc");
        Comparator natural = Ordering.natural();
        //自然排序
        Collections.sort(strList,natural);
        logger.info("自然排序：{}",strList);
        //倒排
//      流式风格
        Comparator reverse = Ordering.natural().reverse();
        Collections.sort(strList,reverse);
        logger.info("自然倒排：{}",strList);

        List<Integer> numberList = Arrays.asList(6,61,28,12,45);
        //求最小元素、最大元素
        logger.info("最大：{}",Ordering.natural().max(numberList));
        logger.info("最小：",Ordering.natural().min(numberList));

//      比较顺序
        logger.info("比较顺序：{}",Ordering.explicit(numberList));

//      复制copy新集合
        logger.info("排序复制到新集合：{}",Ordering.natural().sortedCopy(numberList));
//      倒序后取多少个元素
        logger.info("greatestOf：{}",Ordering.natural().greatestOf(numberList,3));
//      自然排序后取满足指定多少个元素
        logger.info("leastOf：{}",Ordering.natural().leastOf(numberList,3));

//        复杂对象排序支持
        orergingObject();

    }

    /**
     * 针对对象其中单个字段进行排序
     */
    private static void orergingObject() {
        Random random = new Random();
        List<Foo> fooList = Lists.newArrayList();
        for(int i = 0 ; i < 5 ;i++){
            Foo foo = new Foo();
            foo.setAge(random.nextInt(40));
            fooList.add(foo);
        }
        logger.info("原始数据：{}" ,fooList);
        Ordering<Foo> orderingFoo = Ordering.natural().nullsFirst().onResultOf(new Function<Foo, Comparable>() {
            @Override
            public Comparable apply(Foo foo) {
                return foo.getAge();
            }
        }).nullsFirst();
        logger.info("对象字段排序：{}" ,orderingFoo.sortedCopy(fooList));
    }
}
