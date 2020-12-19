
package thread_demo01;

abstract class Person {

	abstract public void add();

}

public class ThreadDemo03 {

	public static void main(String[] args) {
		Person person = new Person() {

			@Override
			public void add() {
				System.out.println("我是add方法");

			}
		};
		person.add();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// 线程需要执行的任务代码
				System.out.println("子线程开始启动....");
				for (int i = 0; i < 30; i++) {
					System.out.println("run i:" + i);
				}
			}
		});
		thread.start();
		System.out.println("主线程开始启动....");
		for (int i = 0; i < 5; i++) {
			System.out.println("main i=" + i);
		}
		System.out.println("主线程执行完毕....");
		// 创建的线程，为和主线程并行执行。
	}

}
