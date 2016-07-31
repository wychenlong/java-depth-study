package com.wanying.study.javolution;

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

	private static int capcatity = 1000000000;

	public static void main(String args[]) {
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
			list.add(String.valueOf(i));
		}
		long end = System.currentTimeMillis();
		System.out.println("jdk list insert string cost time = " + (end - start) + " ms");

	}

	private static void addInt() {
		List list = new ArrayList();
		long start = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("jdk list insert  int cost time = " + (end - start) + " ms");
	}

	private static void query(List<String> list) {
		long start1 = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			list.get(i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("jdk list get string cost time = " + (end1 - start1) + " ms");
	}

}