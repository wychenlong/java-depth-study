package com.wanying.study.guava.basic;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.wanying.study.guava.vo.Person;

/**
 * Created by cl on 2016/7/23.
 */
public class ObjectDemo implements Comparable<ObjectDemo> {
    private int propertiesOne;
    private String propertiesTwo;


    public static void main(String args[]) {
        //Object Equals方法，JDK7也增加此方法
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, null));

        //hashcode
        System.out.println(Objects.hashCode("1", 12, "test"));

        //tostring
        System.out.println(MoreObjects.toStringHelper(Person.class).add("name", "x"));
        System.out.println(new ObjectDemo());
        System.out.println(new Person());


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
