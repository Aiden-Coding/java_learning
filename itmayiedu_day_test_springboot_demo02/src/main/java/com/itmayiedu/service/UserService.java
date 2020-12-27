package com.itmayiedu.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Async // 类似与开启线程执行..
	public void userThread() {
		log.info("##02##");
		try {
			Thread.sleep(5 * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		log.info("##03##");

		// new Thread(new Runnable() {
		// public void run() {
		// log.info("##02##");
		// try {
		// Thread.sleep(5 * 1000);
		// } catch (Exception e) {
		// // TODO: handle exception
		// }
		// log.info("##03##");
		// }
		// }).start();
	}

}
