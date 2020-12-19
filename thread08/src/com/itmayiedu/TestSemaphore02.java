
package com.itmayiedu;

import java.util.Random;
import java.util.concurrent.Semaphore;
class Parent implements Runnable {
	private String name;
	private Semaphore wc;
	public Parent(String name,Semaphore wc){
		this.name=name;
		this.wc=wc;
	}
	@Override
	public void run() {
		try {
			// ʣ�µ���Դ(ʣ�µ�é��)
			int availablePermits = wc.availablePermits();
			if (availablePermits > 0) {
				System.out.println(name+"������Ҳ,������é����...");
			} else {
				System.out.println(name+"��ôû��é����...");
			}
			//����é�� �����Դ�ﵽ3�Σ��͵ȴ�
			wc.acquire();
			System.out.println(name+"���������ϲ�����..ˬ��");
			   Thread.sleep(new Random().nextInt(1000)); // ģ���ϲ���ʱ�䡣
			System.out.println(name+"����������...");
			wc.release();
			
		} catch (Exception e) {

		}
	}
}
public class TestSemaphore02 {
	public static void main(String[] args) {
		// һ������ֻ��3����λ��������10�������ϲ���������ô�죿����10���˵ı�ŷֱ�Ϊ1-10������1���ȵ�������10����󵽲�������ô1-3������ʱ���Ȼ�п��ÿ�λ��˳����ޣ�4������ʱ����Ҫ����ǰ��3���Ƿ����˳����ˣ�������˳�������ȥ������ȴ���ͬ���ĵ���4-10��Ҳ��Ҫ�ȴ������ϲ������˳�������ܽ�ȥ������˭�Ƚ�ȥ��ÿ��ȴ������Ƿ������ʣ��Ƿ��������������ϵĹ���
         Semaphore semaphore = new Semaphore(3);
		for (int i = 1; i <=10; i++) {
			 Parent parent = new Parent("��"+i+"����,",semaphore);
			 new Thread(parent).start();
		}
	}
}
