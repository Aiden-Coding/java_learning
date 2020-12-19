
package thread_demo01;

class StopDemoThread extends Thread {
	volatile boolean flag = true;

	@Override
	public synchronized void run() {
		while (flag) {
			try {
				this.sleep(1000);
				wait();
			} catch (Exception e) {
				e.printStackTrace();
				stopThread();
			}
			System.out.println("run.....");
		}
	}

	public void stopThread() {
		flag = false;
		System.out.println("ֹͣ���߳�");
	}
}

public class StopThread {

	public static void main(String[] args) {
		System.out.println("main �����ɹ�...");
		StopDemoThread stop0 = new StopDemoThread();
		StopDemoThread stop1 = new StopDemoThread();
		stop0.start();
		stop1.start();
		System.out.println("���߳�����....");
		for (int i = 0; i < 35; i++) {
			try {
				Thread.sleep(300);
			} catch (Exception e) {
				// TODO: handle exception
			}

			System.out.println("main i:" + i);
			if (i == 25) {
				// stop0.stopThread();
				// stop1.stopThread();
				stop0.interrupt();
				stop1.interrupt();
			}

		}
	}

}
