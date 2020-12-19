
package thread_demo01;

public class JoinThreadDemo02 {

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 20; i++) {
					System.out.println("t1,i:" + i);
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					t1.join();
				} catch (Exception e) {
					// TODO: handle exception
				}
				for (int i = 0; i < 20; i++) {
					System.out.println("t2,i:" + i);
				}
			}
		});
		Thread t3 = new Thread(new Runnable() {
			public void run() {
				try {
					t2.join();
				} catch (Exception e) {
					// TODO: handle exception
				}
				for (int i = 0; i < 20; i++) {
					System.out.println("t3,i:" + i);
				}
			}
		});
		t1.start();
		t2.start();
		t3.start();
	}

}
