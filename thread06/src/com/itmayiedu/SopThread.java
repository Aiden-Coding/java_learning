
package com.itmayiedu;

class StopThreadDemo extends Thread {
	private volatile boolean flag = true;

	@Override
	public synchronized void run() {
		System.out.println("子线程开始执行...");
		while (flag) {
            try {
            	  wait();
			} catch (Exception e) {
				stopThread();
			}
		}
		System.out.println("子线程结束执行...");
	}

	public void stopThread() {
		System.out.println("调用stoThread方法...");
		this.flag = false;
		System.out.println("已经将flag修改为"+flag);
	}

}

public class SopThread {

	public static void main(String[] args) throws InterruptedException {
		StopThreadDemo stopThreadDemo = new StopThreadDemo();
		stopThreadDemo.start();
	
		for (int i = 1; i <10; i++) {
			System.out.println("我是主线程,i:"+i);
			Thread.sleep(1000);
			if(i==3){
				//当前等待的线程，直接抛出异常
				stopThreadDemo.interrupt();
//				stopThreadDemo.stopThread();
			}
		}
	}

}
