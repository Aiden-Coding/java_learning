
package thread_demo01;

class CreateThreadDemo02 implements Runnable {

	@Override
	public void run() {
		// ������߳���Ҫִ�е�����
		System.out.println("���߳̿�ʼ����....");
		for (int i = 0; i < 30; i++) {
			System.out.println("run i:" + i);
		}
	}

}

public class ThreadDemo02 {

	public static void main(String[] args) {
		CreateThreadDemo02 createThreadDemo02 = new CreateThreadDemo02();
		Thread thread = new Thread(createThreadDemo02);
		thread.start();
		System.out.println("���߳̿�ʼ����....");
		for (int i = 0; i < 5; i++) {
			System.out.println("main i=" + i);
		}
		System.out.println("���߳�ִ�����....");
		// �������̣߳�Ϊ�����̲߳���ִ�С�
	}

}
