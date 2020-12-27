package com.itmayiedu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载springmvc--dispatcherServlert<br>
 * 
 * @作者说明 每特教育-余胜军 <br>
 * @联系方式 qq644064779|www.itmayieducom-蚂蚁课堂<br>
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 加载根配置信息 spring核心
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { RootConfig.class };
	}

	// springmvc 加载 配置信息
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebConfig.class };
	}

	// springmvc 拦截url映射 拦截所有请求
	protected String[] getServletMappings() {

		return new String[] { "/" };

	}

}
