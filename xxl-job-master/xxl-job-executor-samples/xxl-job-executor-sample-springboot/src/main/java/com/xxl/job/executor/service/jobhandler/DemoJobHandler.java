package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤： 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，
 * 注解value值对应的是调度中心新建任务的JobHandler属性的值。 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHandler(value = "demoJobHandler")
@Component
public class DemoJobHandler extends IJobHandler {
	@Value("${xxl.job.executor.port}")
	private String executorPort;

	// @JobHandler 底层实现 value demoJobHandler 名称 对应存放类的class地址
	// com.xxl.job.executor.service.jobhandler 在使用反射机制 执行execute
	// 目的是为了反射统一执行方法
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		// 任务调度执行地址
		System.out.println("####DemoJobHandler####execute()执行 executorPort：" + executorPort);
		return SUCCESS;
	}

}
