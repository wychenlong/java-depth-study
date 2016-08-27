package com.wanying.study.guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.wanying.study.guava.collections.ImmutableDemo;
import com.wanying.study.guava.vo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by cl on 2016/7/23.
 */
public class ObjectDemo implements Comparable<ObjectDemo> {
    private int propertiesOne;
    private String propertiesTwo;
    private static final Logger logger = LoggerFactory.getLogger(ObjectDemo.class);

    public static void main(String args[]) {
        //Object Equals方法，JDK7也增加此方法
        logger.info(String.valueOf(Objects.equal("a", "a")));
        logger.info(String.valueOf(Objects.equal(null, "a")));
        logger.info(String.valueOf(Objects.equal("a", null)));
        logger.info(String.valueOf(Objects.equal(null, null)));

        //hashcode
        logger.info(String.valueOf(Objects.hashCode("1", 12, "test")));

        //tostring
        logger.info(MoreObjects.toStringHelper(Person.class).add("name", "x").toString());
        logger.info(new ObjectDemo().toString());
        logger.info(new Person().toString());


    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this.getClass()).add("propertiesOne", propertiesOne).add("propertiesTwo", propertiesTwo).toString();
    }

    @Override
    public int compareTo(ObjectDemo that) {
        return ComparisonChain.start().compare(this.propertiesOne, that.propertiesOne).
                compare(this.propertiesTwo, that.propertiesTwo, Ordering.<String>natural().nullsLast()).result();
    }

//    @Override
//    public int compareTo(ObjectDemo that){
//
//        int cmp = this.propertiesTwo.compareTo(that.propertiesTwo);
//        if(cmp != 0){
//            return cmp;
//        }
//        return compare(this.propertiesOne,that.propertiesOne);
//    }
//
//    public  int compare(int x, int y) {
//        return (x < y) ? -1 : ((x == y) ? 0 : 1);
//    }
}
