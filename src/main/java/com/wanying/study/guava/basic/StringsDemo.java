package com.wanying.study.guava.basic;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * Created by cl on 2016/7/23.
 */
public class StringsDemo {
    public static void main(String args[]) {
        testStringJoiner();
        testStringSpliter();
    }

    private static void testStringJoiner() {
        //连接器joiner
        List<String> dataList = Lists.newArrayList("A", "B", "C", "    ", null);
        Joiner joiner = Joiner.on("=").skipNulls();
        System.out.println(joiner.join(dataList));
        //map joiner
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("A", "1");
        dataMap.put("B", "2");
        Joiner.MapJoiner mapJoiner = Joiner.on(";").withKeyValueSeparator(":");
        System.out.println(mapJoiner.join(dataMap));
    }

    private static void testStringSpliter() {
        String dataStr = ",a,,b,";
        //忽略尾部空字符串
        String[] jdkSplit = dataStr.split(",");
        System.out.println("JDK 拆分" + Lists.newArrayList(jdkSplit));
        //流式风格
        List<String> splitIterable = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(dataStr);
        System.out.println("Guava 拆分" + splitIterable);

        //拆分为Key,value形式,map
        String mapDataStr = "a:1,b:2";
        Splitter splitter = Splitter.on(',').trimResults().omitEmptyStrings();
        Splitter.MapSplitter mapSplitter = splitter.withKeyValueSeparator(":");
        Map<String, String> stringMap = mapSplitter.split(mapDataStr);
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            System.out.print(" key == " + entry.getKey());
            System.out.print(" value == " + entry.getValue());
            System.out.println();
        }

    }
}
