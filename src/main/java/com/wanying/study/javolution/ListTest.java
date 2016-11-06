package com.wanying.study.javolution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <b>项目名称</b>： java-depth-study <br>
 * <b>包名称</b>： com.wanying.study.javolution.list <br>
 * <b>类名称</b>： ListTest.java <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>：wychenlong <br>
 * <b>创建时间</b>：2016年2月25日 下午2:08:15 <br>
 * <b>修改人</b>： <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 * 
 * @version 1.0.0 <br>
 * 
 */
public class ListTest {

	private static final Logger logger = LoggerFactory.getLogger(ListTest.class);
	private static int capcatity = 1000000000;

	public static void main(String args[]) {
		String str = "370000100000000800170941";
		System.out.println(str.getBytes().length);
		addString();
		// addInt();
		try {
			Thread.sleep(100000000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void addString() {
		List<String> list = new ArrayList<String>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			list.add(String.valueOf("360008956235426"+i));
		}
		long end = System.currentTimeMillis();
		logger.info("jdk list insert string cost time = " + (end - start) + " ms");

	}

	private static void addInt() {
		List list = new ArrayList();
		long start = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		logger.info("jdk list insert  int cost time = " + (end - start) + " ms");
	}

	private static void query(List<String> list) {
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			list.get(i);
		}
		long end1 = System.currentTimeMillis();
		logger.info("jdk list get string cost time = " + (end1 - start1) + " ms");
	}

}
