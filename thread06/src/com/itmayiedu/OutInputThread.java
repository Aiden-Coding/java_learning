
package com.itmayiedu;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 
 * @classDesc: 功能描述:(共享资源实体类)
 * @author: 余胜军
 * @createTime: 2017年12月5日 下午9:22:28
 * @version: v1.0
 * @copyright:蚂蚁课堂(每特学院)上海每特教育科技有限公司出品
 * @website:www.itmayiedu.com或者www.meiteedu.com
 * @weixin:官方微信号 每特学院
 * @QQ:644064779 QQ群:116295598
 */
class Res {

	public String userName;
	public String sex;
	// true 生产者线程等待，消費可以进行消费 false 生成者可以写， 消费者变为等待
	public boolean flag = false;
	Lock lock = new ReentrantLock();
	
}

class Out extends Thread {
	Res res;
	Condition newCondition;
	public Out(Res res,Condition newCondition) {
		this.res = res;
		this.newCondition=newCondition;
	}

	@Override
	public void run() {
		// 写的操作 0 1
		int count = 0;
		while (true) {
			try {
				res.lock.lock();
				if (res.flag) {
					try {
						newCondition.await();;// 让当前线程 从运行状态变为休眠状态 并且释放锁的资源
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
			
				if (count == 0) {
					res.userName = "小红";
					res.sex = "女";
				} else {
					res.userName = "余胜军";
					res.sex = "男";
				}
				// 计算奇数或者偶数公式
				count = (count + 1) % 2;
				res.flag = true;
				// 唤醒
				newCondition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				res.lock.unlock();
			}
			
		}

	}

}

class Input extends Thread {
	Res res;
	Condition newCondition;
	public Input(Res res,Condition newCondition) {
		this.res = res;
		this.newCondition=newCondition;
	}

	@Override
	public void run() {
		while (true) {
			try {
				// 上锁
				res.lock.lock();
				if (!res.flag) {
					try {
						newCondition.await();
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			
				System.out.println(res.userName + "," + res.sex);
				res.flag = false;
				newCondition.signal();
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				// 释放锁
				res.lock.unlock();
			}
		
		}

	}
}

public class OutInputThread {
	public static void main(String[] args) {
		Res res = new Res();
		Condition newCondition = res.lock.newCondition();
		Out out = new Out(res,newCondition);
	
		Input input = new Input(res,newCondition);
		out.start();
		input.start();
	}

}
