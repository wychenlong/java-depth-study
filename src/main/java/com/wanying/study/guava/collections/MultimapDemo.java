package com.wanying.study.guava.collections;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wychenlong on 2016/9/5.
 */
public class MultimapDemo {

    private static final Logger logger = LoggerFactory.getLogger(MultimapDemo.class);

    public static void main(String args[]) {
        testMultiMap();
    }

    /**
     *  multimap 相当于 Map<String,List<String>> stringListMap = Maps.newHashMap();
     */
    private static void testMultiMap() {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        for (int i = 0; i < 5; i++) {
            multimap.put("A", i + "");
        }
        for (int i = 0; i < 10; i++) {
            multimap.put("B", i + "");
        }

        logger.info("mutilmap reult={}",multimap);

    }
}
