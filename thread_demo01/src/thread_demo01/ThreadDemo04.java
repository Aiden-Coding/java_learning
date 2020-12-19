
package thread_demo01;

/**
 * 
 * @classDesc: 功能描述:(守护线程demo)
 * @author: 余胜军
 * @createTime: 2017年12月2日 上午1:20:42
 * @version: v1.0
 * @copyright:蚂蚁课堂(每特学院)上海每特教育科技有限公司出品
 * @website:www.itmayiedu.com或者www.meiteedu.com
 * @weixin:官方微信号 每特学院
 * @QQ:644064779 QQ群:116295598
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
					System.out.println("我是子线程....");
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
			System.out.println("我是主线程....i:"+i);
		}
	}

}
