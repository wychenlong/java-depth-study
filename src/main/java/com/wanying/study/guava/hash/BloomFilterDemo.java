package com.wanying.study.guava.hash;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;
import com.wanying.study.guava.vo.Person;

import java.util.List;

/**
 * Created by wychenlong on 2016/7/26.
 */
public class BloomFilterDemo {
    public static void main(String args[]){
        Funnel<Person> personFunnel = new Funnel<Person>(){
            @Override
            public void funnel(Person person, PrimitiveSink primitiveSink) {
                primitiveSink.putInt(person.getAge());
            }
        };

        BloomFilter<Person> bloomFilter = BloomFilter.create(personFunnel,500,0.01);
        List<Person> personList = getDataList();
        for(Person person : personList){
            bloomFilter.put(person);
        }

        Person equalsPerson = new Person();
        equalsPerson.setName("name1");
        equalsPerson.setAge(2001);

        System.out.print("布隆过滤器，元素是否存在"+bloomFilter.mightContain(equalsPerson));

    }

    private static List<Person> getDataList(){
        List<Person> personList = Lists.newArrayList();
        for(int i = 0; i < 5 ; i++){
            Person p = new Person();
            p.setAge(2000+i);
            p.setName("name"+i);
            personList.add(p);
        }
        return personList;
    }
}
