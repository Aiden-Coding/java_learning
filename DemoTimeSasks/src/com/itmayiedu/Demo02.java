package com.itmayiedu;

import java.util.Timer;
import java.util.TimerTask;

/**
 * ʹ��TimerTask��ʵ�ֶ�ʱ����
 * 
 * @author Administrator
 *
 */
public class Demo02 {
	static long count = 0;

	public static void main(String[] args) {
		TimerTask timerTask = new TimerTask() {

			@Override
			public void run() {
				count++;
				System.out.println(count);
			}
		};
		Timer timer = new Timer();
		// ����
		long delay = 0;
		// ����
		long period = 1000;
		timer.scheduleAtFixedRate(timerTask, delay, period);
	}

}
