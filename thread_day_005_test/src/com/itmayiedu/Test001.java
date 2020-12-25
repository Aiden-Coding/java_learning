package com.itmayiedu;

class Res {
	public String userSex;
	public String userName;
	public boolean flag = false;
}

class IntThread extends Thread {
	private Res res;

	public IntThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		int count = 0;
		while (true) {
			synchronized (res) {
				if (res.flag) {
					try {
						// ��ǰ�̱߳��ȴ���ע��һ��Ҫ�Ͷ�����(������һ��ʹ��),ጷ�������Դ
						res.wait();
					} catch (Exception e) {

					}
				}
				if (count == 0) {
					res.userName = "С��";
					res.userSex = "Ů";
				} else {
					res.userName = "С��";
					res.userSex = "��";
				}
				count = (count + 1) % 2;
				res.flag = true;
				//���ѵ�ǰ���ȴ����߳�
				res.notify();
			}

		}
	}

}

class OutThread extends Thread {
	private Res res;

	public OutThread(Res res) {
		this.res = res;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (res) {
				if (!res.flag) {
					try {
						res.wait();
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				System.out.println(res.userName + "," + res.userSex);
				res.flag = false;
				res.notify();
			}

		}
	}

}

public class Test001 {

	public static void main(String[] args) {
		Res res=new Res();
		res.notify();
//		Res res = new Res();
//		IntThread intThread = new IntThread(res);
//		OutThread outThread = new OutThread(res);
//		intThread.start();
//		outThread.start();
	}

}
