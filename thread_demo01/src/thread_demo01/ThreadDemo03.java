
package thread_demo01;

abstract class Person {

	abstract public void add();

}

public class ThreadDemo03 {

	public static void main(String[] args) {
		Person person = new Person() {

			@Override
			public void add() {
				System.out.println("����add����");

			}
		};
		person.add();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// �߳���Ҫִ�е��������
				System.out.println("���߳̿�ʼ����....");
				for (int i = 0; i < 30; i++) {
					System.out.println("run i:" + i);
				}
			}
		});
		thread.start();
		System.out.println("���߳̿�ʼ����....");
		for (int i = 0; i < 5; i++) {
			System.out.println("main i=" + i);
		}
		System.out.println("���߳�ִ�����....");
		// �������̣߳�Ϊ�����̲߳���ִ�С�
	}

}
