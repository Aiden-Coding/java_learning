package com.itmayiedu;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ʹ��ScheduledExecutorService�Ǵ�Java
 * SE5��java.util.concurrent���Ϊ���������౻�����ģ�����������Ķ�ʱ����ʵ�ַ�ʽ��
 * 
 * @author Administrator
 *
 */
public class Demo003 {
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				System.out.println("Hello !!");
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// �ڶ�������Ϊ�״�ִ�е���ʱʱ�䣬����������Ϊ��ʱִ�еļ��ʱ��
		service.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
	}
}
