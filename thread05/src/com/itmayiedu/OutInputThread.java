
package com.itmayiedu;

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
}

class Out extends Thread {
	Res res;

	public Out(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		// д�Ĳ��� 0 1
		int count = 0;
		while (true) {
			synchronized (res) {
				if (res.flag) {
					try {
						res.wait();// �õ�ǰ�߳� ������״̬��Ϊ����״̬ �����ͷ�������Դ
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
				res.notify();
			}

		}

	}

}

class Input extends Thread {
	Res res;

	public Input(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res) {
				if (!res.flag) {
					try {
						res.wait();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				System.out.println(res.userName + "," + res.sex);
				res.flag = false;
				res.notify(); // ���ѵ�ǰ�ȴ����̡߳�
			}
		}

	}
}

public class OutInputThread {
	public static void main(String[] args) {
		Res res = new Res();
		Out out = new Out(res);
		Input input = new Input(res);
		out.start();
		input.start();
	}

}
