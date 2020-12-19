
package com.itmayiedu;

import java.util.ArrayList;
import java.util.List;

import com.itmayiedu.entity.UserEntity;

class UserThread implements Runnable {
	private List<UserEntity> listUser;

	public UserThread(List<UserEntity> listUser) {
		this.listUser = listUser;
	}

	@Override
	public void run() {
		for (UserEntity userEntity : listUser) {
			System.out.println(Thread.currentThread().getName() + "---" + userEntity.toString());
		}
	}

}

public class Batch {

	public static void main(String[] args) {
		// 1.初始化用户
		List<UserEntity> listUser = init();
		// 2.定义每个线程发送多少用戶
		int threadSendUserCount = 2;
		// 3.计算多少隔阂线程
		List<List<UserEntity>> listUserThread = ListUtils.splitList(listUser, threadSendUserCount);
		for (int i = 0; i < listUserThread.size(); i++) {
			List<UserEntity> list = listUserThread.get(i);
			// 4.分配给每个线程进发送
			UserThread userThread = new UserThread(list);
			new Thread(userThread).start();
		}

	}

	private static List<UserEntity> init() {
		List<UserEntity> listUser = new ArrayList<UserEntity>();
		for (int i = 1; i <= 11; i++) {
			listUser.add(new UserEntity("userId:" + i, "userName:" + i));
		}
		return listUser;
	}

}
