
package com.itmayiedu;

import java.text.DecimalFormat;

//设置最大堆内存大小
public class Demo0001 {

	public static void main(String[] args) throws InterruptedException {
		// 分配1M
		byte[] bytes01 = new byte[1 * 1024 * 1024];
		System.out.println("分配1m");
		getJvmInfo();
		Thread.sleep(3000);
		// 分配4M
		byte[] bytes02 = new byte[1024 * 1024 * 4];
		System.out.println("分配4m");
		Thread.sleep(3000);
		getJvmInfo();
	}

	public static void getJvmInfo() {
		// 获取最大内存
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("maxMemory:" + maxMemory + ",转换成" + toM(maxMemory) + "M");
		// 当前空闲内存
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemory:" + freeMemory + ",转换成" + toM(freeMemory) + "M");
		// 已使用内存
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("totalMemory:" + totalMemory + ",转换成" + toM(totalMemory) + "M");
	}

	public static String toM(long maxMemory) {
		float num = maxMemory / (1024 * 1024);
		DecimalFormat dlf = new DecimalFormat("0.00");
		String result = dlf.format(num);
		return result;
	}

}
