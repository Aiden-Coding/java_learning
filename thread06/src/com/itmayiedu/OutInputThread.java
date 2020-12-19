
package com.itmayiedu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @classDesc: ��������:(������Դʵ����)
 * @author: ��ʤ��
 * @createTime: 2017��12��5�� ����9:22:28
 * @version: v1.0
 * @copyright:���Ͽ���(ÿ��ѧԺ)�Ϻ�ÿ�ؽ����Ƽ����޹�˾��Ʒ
 * @website:www.itmayiedu.com����www.meiteedu.com
 * @weixin:�ٷ�΢�ź� ÿ��ѧԺ
 * @QQ:644064779 QQȺ:116295598
 */
class Res {

	public String userName;
	public String sex;
	// true �������̵߳ȴ������M���Խ������� false �����߿���д�� �����߱�Ϊ�ȴ�
	public boolean flag = false;
	Lock lock = new ReentrantLock();
	
}

class Out extends Thread {
	Res res;
	Condition newCondition;
	public Out(Res res,Condition newCondition) {
		this.res = res;
		this.newCondition=newCondition;
	}

	@Override
	public void run() {
		// д�Ĳ��� 0 1
		int count = 0;
		while (true) {
			try {
				res.lock.lock();
				if (res.flag) {
					try {
						newCondition.await();;// �õ�ǰ�߳� ������״̬��Ϊ����״̬ �����ͷ�������Դ
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			
				if (count == 0) {
					res.userName = "С��";
					res.sex = "Ů";
				} else {
					res.userName = "��ʤ��";
					res.sex = "��";
				}
				// ������������ż����ʽ
				count = (count + 1) % 2;
				res.flag = true;
				// ����
				newCondition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				res.lock.unlock();
			}
			
		}

	}

}

class Input extends Thread {
	Res res;
	Condition newCondition;
	public Input(Res res,Condition newCondition) {
		this.res = res;
		this.newCondition=newCondition;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// ����
				res.lock.lock();
				if (!res.flag) {
					try {
						newCondition.await();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			
				System.out.println(res.userName + "," + res.sex);
				res.flag = false;
				newCondition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				// �ͷ���
				res.lock.unlock();
			}
		
		}

	}
}

public class OutInputThread {
	public static void main(String[] args) {
		Res res = new Res();
		Condition newCondition = res.lock.newCondition();
		Out out = new Out(res,newCondition);
	
		Input input = new Input(res,newCondition);
		out.start();
		input.start();
	}

}
