package com.itmayiedu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 加载SpringMVCDispatcherServlet
 * 
 * @作者说明 每特教育-余胜军 <br>
 * @联系方式 qq644064779|www.itmayieducom-蚂蚁课堂<br>
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// 加载根容器
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] { RootConfig.class };
	}

	// 加载SpringMVC容器
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebConfig.class };
	}

	// SpringMVCDispatcherServlet 拦截的请求 /
	protected String[] getServletMappings() {

		return new String[] { "/" };
	}

}
