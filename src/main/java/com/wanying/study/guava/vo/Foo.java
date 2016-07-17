package com.wanying.study.guava.vo;

/**
 * Created by wychenlong on 2016/7/13.
 */
public class Foo {
    Integer age;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "age=" + age +
                '}';
    }
}
