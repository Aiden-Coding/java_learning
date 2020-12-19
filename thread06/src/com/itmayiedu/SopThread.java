
package com.itmayiedu;

class StopThreadDemo extends Thread {
	private volatile boolean flag = true;

	@Override
	public synchronized void run() {
		System.out.println("���߳̿�ʼִ��...");
		while (flag) {
            try {
            	  wait();
			} catch (Exception e) {
				stopThread();
			}
		}
		System.out.println("���߳̽���ִ��...");
	}

	public void stopThread() {
		System.out.println("����stoThread����...");
		this.flag = false;
		System.out.println("�Ѿ���flag�޸�Ϊ"+flag);
	}

}

public class SopThread {

	public static void main(String[] args) throws InterruptedException {
		StopThreadDemo stopThreadDemo = new StopThreadDemo();
		stopThreadDemo.start();
	
		for (int i = 1; i <10; i++) {
			System.out.println("�������߳�,i:"+i);
			Thread.sleep(1000);
			if(i==3){
				//��ǰ�ȴ����̣߳�ֱ���׳��쳣
				stopThreadDemo.interrupt();
//				stopThreadDemo.stopThread();
			}
		}
	}

}
