package com.wanying.study.guava.basic;

import com.google.common.base.Preconditions;

/**
 * Created by cl on 2016/5/15.
 * 前置条件检查
 */
public class PreconditionsDemo {

    public  static void main(String args[]){
        //布尔表达式检测
        Preconditions.checkArgument(0.8 >= 0,"amount value: %s", 0.8);
        //检查数组或者list或者字符串指定位置是否存在元素,这三者都是0开始
        String strcheck = "2 ";
        Preconditions.checkElementIndex(1,strcheck.length());
        //检查引用是否为空，比如入参对象,有重载的方法，格式化错误信息
        Preconditions.checkNotNull(strcheck);
    }




}
