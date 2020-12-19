package thread_demo01;

/**
 * 
 * @classDesc: 功能描述:()
 * @author: 余胜军
 * @createTime: 2017年12月1日 下午6:20:36
 * @version: v1.0
 * @copyright:蚂蚁课堂(每特学院)上海每特教育科技有限公司出品
 * @website:www.itmayiedu.com或者www.meiteedu.com
 * @weixin:官方微信号 每特学院
 * @QQ:644064779 QQ群:116295598
 */
class CreateThread01 extends Thread {
	// 1.继承Thread类 重写run方法
	// 2.实现runlabel接口 重写run方法
	// 3.使用匿名内部类的方式

	@Override
	public void run() {
		// 具体的线程需要执行的任务
		System.out.println("子线程开始启动....");
		for (int i = 0; i < 30; i++) {
			System.out.println("run i:" + i);
		}
	}

}

public class ThreadDemo01 {

	public static void main(String[] args) {
		// 什么是线程？ 其实就是程序中 执行的一条路径。
		// 一个进程中，一定main主线程 GC线程属于守护线程
		// 什麼是守护线程，主线程或者进程停止 该线程就会停止。
		// 用户线程 用户自定义的线程--------特征：主线程停止，用户线程不会受影响。
		System.out.println("main方法启动...");
		CreateThread01 createThread01 = new CreateThread01();
		// 开启线程调用start(),
		createThread01.start();
		System.out.println("主线程开始启动....");
		for (int i = 0; i <5; i++) {
			System.out.println("main i=" + i);

		}
		System.out.println("主线程执行完毕....");
		// 创建的线程，为和主线程并行执行。 
	}

}
