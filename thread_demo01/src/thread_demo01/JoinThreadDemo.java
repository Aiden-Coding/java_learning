
package thread_demo01;

class JoinThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getId() + "--" + i);
		}
	}
}

public class JoinThreadDemo {

	public static void main(String[] args) {
		JoinThread t1 = new JoinThread();
		JoinThread t2 = new JoinThread();

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (int i = 0; i < 100; i++) {
			System.out.println("main --" + i);
		}
	}

}
