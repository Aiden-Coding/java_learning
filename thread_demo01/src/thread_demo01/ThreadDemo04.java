
package thread_demo01;

/**
 * 
 * @classDesc: ��������:(�ػ��߳�demo)
 * @author: ��ʤ��
 * @createTime: 2017��12��2�� ����1:20:42
 * @version: v1.0
 * @copyright:���Ͽ���(ÿ��ѧԺ)�Ϻ�ÿ�ؽ����Ƽ����޹�˾��Ʒ
 * @website:www.itmayiedu.com����www.meiteedu.com
 * @weixin:�ٷ�΢�ź� ÿ��ѧԺ
 * @QQ:644064779 QQȺ:116295598
 */
public class ThreadDemo04 {

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (Exception e) {
						// TODO: handle exception
					}
					System.out.println("�������߳�....");
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
		for (int i = 0; i < 10; i++) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
			System.out.println("�������߳�....i:"+i);
		}
	}

}
