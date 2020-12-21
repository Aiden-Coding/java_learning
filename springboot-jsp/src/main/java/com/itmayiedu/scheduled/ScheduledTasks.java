
package com.itmayiedu.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

//@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = 1000)
	public void test() {
      // 执行任务调度方法
		System.out.println("我正在每隔1秒打印...");
	}

}
