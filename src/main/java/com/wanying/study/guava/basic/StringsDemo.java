package com.wanying.study.guava.basic;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by cl on 2016/7/23.
 */
public class StringsDemo {

    private static final Logger logger = LoggerFactory.getLogger(StringsDemo.class);
    public static void main(String args[]) {
        testStringJoiner();
        testStringSpliter();
    }

    private static void testStringJoiner() {
        //连接器joiner
        List<String> dataList = Lists.newArrayList("A", "B", "C", "    ", null);
        Joiner joiner = Joiner.on("=").skipNulls();
        logger.info(joiner.join(dataList));
        //map joiner
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("A", "1");
        dataMap.put("B", "2");
        Joiner.MapJoiner mapJoiner = Joiner.on(";").withKeyValueSeparator(":");
        logger.info(mapJoiner.join(dataMap));
    }

    private static void testStringSpliter() {
        String dataStr = ",a,,b,";
        //忽略尾部空字符串
        String[] jdkSplit = dataStr.split(",");
        logger.info("JDK 拆分:{}" , Lists.newArrayList(jdkSplit));
        //流式风格
        List<String> splitIterable = Splitter.on(',').trimResults().omitEmptyStrings().splitToList(dataStr);
        logger.info("Guava 拆分 {}" ,splitIterable);

        //拆分为Key,value形式,map
        String mapDataStr = "a:1,b:2";
        Splitter splitter = Splitter.on(',').trimResults().omitEmptyStrings();
        Splitter.MapSplitter mapSplitter = splitter.withKeyValueSeparator(":");
        Map<String, String> stringMap = mapSplitter.split(mapDataStr);
        for (Map.Entry<String, String> entry : stringMap.entrySet()) {
            logger.info(" key == {}",entry.getKey());
            logger.info(" value == {}" ,entry.getValue());

        }

    }
}
