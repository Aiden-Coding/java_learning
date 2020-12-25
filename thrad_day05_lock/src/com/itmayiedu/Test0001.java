package com.itmayiedu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// �������
class Res {

	// ����
	public String name;
	// �Ա�
	public String sex;
	// Ϊtrue����� �����������д
	// Ϊfalse����� ����д�����ܶ���
	public boolean flag = false;
	public Lock lock = new ReentrantLock();
	public Condition condition=lock.newCondition();
}

// �������߳�
class IntThread extends Thread {
	public Res res;

	public IntThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		int count = 0; // 1
		while (true) {
			try {
				res.lock.lock();// ����
				if(res.flag){
					res.condition.await();
				}
				if (count == 0) {
					res.name = "С��";
					res.sex = "Ů";
				} else {
					res.name = "С��";
					res.sex = "��";
				}
				count = (count + 1) % 2;// 0 1 0 1 0 1
				res.flag=true;
				res.condition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				res.lock.unlock();// �ͷŵ�ǰ��
			}

		}

	}

}

// ��ȡ�߳�
class OutThread extends Thread {

	public Res res;

	public OutThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {

			try {
				res.lock.lock();
                if(!res.flag){
                	res.condition.await();	
                }
                Thread.sleep(1000);
				System.out.println(res.name + "," + res.sex);
				res.flag = false;
				res.condition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				res.lock.unlock();
			}
		}

	}
}

public class Test0001 {
	public synchronized static void main(String[] args) throws InterruptedException {

		Res res = new Res();
		IntThread intThread = new IntThread(res);
		OutThread outThread = new OutThread(res);
		intThread.start();
		outThread.start();
	}
}
