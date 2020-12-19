
package com.itmayiedu;

class ThreadVolatileTest extends Thread {
	public volatile  boolean flag = true;

	@Override
	public void run() {
		System.out.println("开始子线程执行开始....");
		while (flag) {

		}
		System.out.println("子线程执行结束....");
	}
    public void stopThread(boolean flag){
    	this.flag=flag;
    }
}

public class ThreadVolatile {
 
	 public static void main(String[] args) throws InterruptedException {
		   ThreadVolatileTest t1 = new ThreadVolatileTest();
		   t1.start();
		   Thread.sleep(3000);
		   t1.stopThread(false);
		   System.out.println("已经设置flag为flase");
		   Thread.sleep(1000);
		   System.out.println("flag:"+t1.flag);
	}
	
}
