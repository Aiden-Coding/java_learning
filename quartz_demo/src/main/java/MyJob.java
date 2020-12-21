import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJob implements Job {
   
	// 执行任务调度方法
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("quartz MyJob date:" + new Date().getTime());
	}

}
