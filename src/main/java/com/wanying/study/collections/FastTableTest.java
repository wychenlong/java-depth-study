package com.wanying.study.collections;

import javolution.util.FastTable;

/**
 * 
 * <b>项目名称</b>： java-depth-study <br>
 * <b>包名称</b>： com.wanying.study.collections <br>
 * <b>类名称</b>： FastTableTest.java <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>：wychenlong <br>
 * <b>创建时间</b>：2016年2月27日 上午9:53:45 <br>
 * <b>修改人</b>： <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 * 
 * @version 1.0.0 <br>
 * 
 */
public class FastTableTest {
	private static int capcatity = 10000000;

	public static void main(String args[]) {
		addString();
		addSource();
		try {
			Thread.sleep(100000l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	private static void addSource(){
		FastTable<String> table = new FastTable<String>();
		table.add("1");
		table.add("2");
		table.add("3");
		table.add("4");
		String str = table.get(2);
	}
	
	private static void addString() {
		FastTable<String> table = new FastTable<String>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < capcatity; i++) {
			table.add(String.valueOf(i));
		}
		long end = System.currentTimeMillis();
		System.out.println("fasttable insert string cost time = " + (end - start) + " ms");
		System.out.println(table.get(10));
	}
}
