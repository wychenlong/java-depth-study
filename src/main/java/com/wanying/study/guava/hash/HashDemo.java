package com.wanying.study.guava.hash;

import com.google.common.hash.*;
import com.wanying.study.guava.vo.Person;

/**
 * Created by cl on 2016/7/17.
 */
public class HashDemo {
    public static void main(String args[]){
        //获取md5函数
        HashFunction md5fucntion = Hashing.md5();
        //获取hasher，存放数据
        Hasher mdHasher = md5fucntion.newHasher();
        mdHasher.putFloat(0.6f);
        mdHasher.putLong(3432l);
        //使用对象原始字段值
        Funnel<Person> personFunnel = new Funnel<Person>(){
            @Override
            public void funnel(Person person, PrimitiveSink primitiveSink) {
                primitiveSink.putInt(person.getAge());
            }
        };
        Person person = new Person();
        person.setAge(56);
        mdHasher.putObject(person,personFunnel);
        System.out.println(mdHasher.hash());
    };
}
