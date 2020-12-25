package com.itmayiedu;

// �������
class Res {

	// ����
	public String name;
	// �Ա�
	public String sex;
	// Ϊtrue����� �����������д
	// Ϊfalse����� ����д�����ܶ���
	public boolean flag = false;

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
			synchronized (res) {
				if (res.flag) {
					try {
						res.wait();// ጷŵ�ǰ������
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				if (count == 0) {
					res.name = "С��";
					res.sex = "Ů";
				} else {
					res.name = "С��";
					res.sex = "��";
				}
				count = (count + 1) % 2;// 0 1 0 1 0 1
				res.flag = true;// ��ǵ�ǰ�߳�Ϊ�ȴ�
				res.notify();// ���ѱ��ȴ����߳�
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
			synchronized (res) {
				try {
					if (!res.flag) {
						res.wait();
					}
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}

				System.out.println(res.name + "," + res.sex);
				res.flag = false;
				res.notify();
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
