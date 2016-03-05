package com.wanying.study.collections;

/**  
 *   
 * <b>项目名称</b>： java-depth-study <br>
 * <b>包名称</b>： com.wanying.study.collections <br>
 * <b>类名称</b>： Test.java <br>
 * <b>类描述</b>：  <br>
 * <b>创建人</b>：wychenlong <br>
 * <b>创建时间</b>：2016年3月1日 下午4:37:23 <br>
 * <b>修改人</b>：  <br>
 * <b>修改时间</b>：  <br>
 * <b>修改备注</b>：  <br>
 * @version 1.0.0  <br>
 *   
 */
public class Test {
	public static void main(String args[]){
		String regex = "固定型是否保本：否";
		String[] t = regex.split(" ");
		System.out.println(t[0]);
	}
}
