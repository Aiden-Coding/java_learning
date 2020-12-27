package com.itmayiedu.sms;

import java.util.Date;

//测试代码
public class ClientTest002 {

	public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
		UserEntity user1 = new UserEntity("张三", new Date());
		System.out.println("user1:" + DateUtils.dateToTimeStamp(user1.getDate()) + "," + user1 + ",userName:"
				+ user1.getUserName());
		Thread.sleep(3000);
		user1.setDate(new Date());
		UserEntity user2 = (UserEntity) user1.clone();
		user2.setDate(new Date());

		System.out.println("user2:" + DateUtils.dateToTimeStamp(user2.getDate()) + "," + user2 + ",userName:"
				+ user2.getUserName());
		System.out.println("user1:" + DateUtils.dateToTimeStamp(user1.getDate()) + "," + user1 + ",userName:"
				+ user1.getUserName());
	}

}
