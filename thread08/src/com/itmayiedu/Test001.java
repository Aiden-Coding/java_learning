
package com.itmayiedu;

import java.util.concurrent.CyclicBarrier;

class Writer extends Thread {
	private CyclicBarrier cyclicBarrier;
	public Writer(CyclicBarrier cyclicBarrier){
		 this.cyclicBarrier=cyclicBarrier;
	}
	@Override
	public void run() {
		System.out.println("�߳�" + Thread.currentThread().getName() + ",����д������");
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("�߳�" + Thread.currentThread().getName() + ",д�����ݳɹ�.....");
		
		try {
			cyclicBarrier.await();
		} catch (Exception e) {
		
		}
		System.out.println("�����߳�ִ�����..........");
	}

}

public class Test001 {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier=new CyclicBarrier(5);
		for (int i = 0; i < 5; i++) {
			Writer writer = new Writer(cyclicBarrier);
			writer.start();
		}
	}

}
