package com.itmayiedu;

/**
 * 使用Thread方式实现定时任务
 * 
 * @author Administrator
 *
 */
public class Demo01 {
	static long count = 0;
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
						count++;
						System.out.println(count);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
