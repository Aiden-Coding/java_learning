package com.itmayiedu;

public class ThreadDemo005 extends Thread {

	public volatile static boolean FLAG = true;

	@Override
	public void run() {
		System.out.println("��ʼ���߳�....");
		while (FLAG) {

		}
		System.out.println("�߳�ֹͣ...");
	}

	public void setRuning(boolean flag) {
		this.FLAG = flag;
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadDemo005 threadDemo005 = new ThreadDemo005();
		threadDemo005.start();
		Thread.sleep(3000);
		threadDemo005.setRuning(false);
		System.out.println("״̬�Ѿ�����Ϊfalse");
		Thread.sleep(1000);
		System.out.println("flag:"+ThreadDemo005.FLAG);
	}
}
