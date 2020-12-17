package com.itmayiedu;

import java.text.DecimalFormat;

/**
 * 查看jvm 堆内存大小F
 * 
 * @author Administrator
 *
 */
public class JVMDemo01 {

	public static void main(String[] args) throws InterruptedException {
//		//获取到当前运行环境最大的堆内存
//		System.out.println("maxMemory:"+Runtime.getRuntime().maxMemory()/(1024*1024));
//		//当前空闲内存
//		System.out.println("freeMemory:"+Runtime.getRuntime().freeMemory());
//		//获取到当前使用多少内存
//		System.out.println("totalMemory:"+Runtime.getRuntime().totalMemory());
		//jvm默认启动4m
		byte[] bytes1=new byte[1*1024*1024];
		System.out.println("分配1m");
		jvmInfo();
		//Thread.sleep(3000);
		byte[] bytes2=new byte[4*1024*1024];
		System.out.println("分配4m");
		jvmInfo();
		//Thread.sleep(000);
		byte[] bytes3=new byte[20*1024*1024];
		System.out.println("分配20m");
		jvmInfo();
	}
	/**
	 * 转换为m
	 * 
	 * @param maxMemory
	 * @return
	 */
	static private String toM(long maxMemory) {
		float num = (float) maxMemory / (1024 * 1024);
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
		String s = df.format(num);// 返回的是String类型
		return s;
	}

	static private void jvmInfo() {
		// 最大内存
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("maxMemory:" + maxMemory + ",转换为M:" + toM(maxMemory));
		// 当前空闲内存
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemory:" +freeMemory+",转换为M:"+toM(freeMemory));
		// 已经使用内存
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("totalMemory:" +totalMemory+",转换为M:"+toM(totalMemory));
	}


}
