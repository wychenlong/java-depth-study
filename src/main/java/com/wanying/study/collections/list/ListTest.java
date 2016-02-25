package com.wanying.study.collections.list;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <b>项目名称</b>： java-depth-study <br>
 * <b>包名称</b>： com.wanying.study.collections.list <br>
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

	private static int capcatity = 100000;

	public static void main(String args[]) {
		testAdd();
	}

	private static void testAdd() {
		List list = new ArrayList();
		long start = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			list.add(i);
		}
		long end = System.currentTimeMillis();
		System.out.println("jdk list insert cost time = "+(end-start));
		System.out.println();
	}

	private static void testCapcatity(int oldCapacity) {
		int newCapacity = (oldCapacity * 3) / 2 + 1;
		System.out.println((oldCapacity * 3) / 2);
		System.out.println(newCapacity);
	}
}
