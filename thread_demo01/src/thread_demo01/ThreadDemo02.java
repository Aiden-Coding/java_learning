
package thread_demo01;

class CreateThreadDemo02 implements Runnable {

	@Override
	public void run() {
		// 具体的线程需要执行的任务
		System.out.println("子线程开始启动....");
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
		System.out.println("主线程开始启动....");
		for (int i = 0; i < 5; i++) {
			System.out.println("main i=" + i);
		}
		System.out.println("主线程执行完毕....");
		// 创建的线程，为和主线程并行执行。
	}

}
