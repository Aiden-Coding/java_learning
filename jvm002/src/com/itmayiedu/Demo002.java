
package com.itmayiedu;

import java.text.DecimalFormat;

public class Demo002 {

	public static void main(String[] args) throws InterruptedException {
		byte[] bytes01 = new byte[1 * 1024 * 1024];
		System.out.println("分配了1M内存");
		jvmInfo();
		Thread.sleep(3000);
		byte[] bytes02 = new byte[4 * 1024 * 1024];
		System.out.println("分配了4M内存");
		jvmInfo();
	}

	static private String toM(long maxMemory) {
		float num = (float) maxMemory / (1024 * 1024);
		DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
		String s = df.format(num);// 返回的是String类型
		return s;
	}

	public static void jvmInfo() {
		// 最大内存配置信息
		long maxMemory = Runtime.getRuntime().maxMemory();
		System.out.println("maxMemory:" + maxMemory + "," + toM(maxMemory) + "M");
		// 当前空闲内存
		long freeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemory:" + freeMemory + "," + toM(freeMemory) + "M");
		//已使用内存
		long totalMemory = Runtime.getRuntime().totalMemory();
		System.out.println("totalMemory:" + totalMemory + "," + toM(totalMemory) + "M");
	}
}
