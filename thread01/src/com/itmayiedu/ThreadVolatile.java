
package com.itmayiedu;

class ThreadVolatileTest extends Thread {
	public volatile  boolean flag = true;

	@Override
	public void run() {
		System.out.println("��ʼ���߳�ִ�п�ʼ....");
		while (flag) {

		}
		System.out.println("���߳�ִ�н���....");
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
		   System.out.println("�Ѿ�����flagΪflase");
		   Thread.sleep(1000);
		   System.out.println("flag:"+t1.flag);
	}
	
}
