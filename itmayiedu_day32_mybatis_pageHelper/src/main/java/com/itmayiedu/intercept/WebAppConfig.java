package com.itmayiedu.intercept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器 军<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@Configuration
public class WebAppConfig {
	@Autowired
	private LoginIntercept loginIntercept;

	// Mvc----MVC---
	@Bean
	public WebMvcConfigurer WebMvcConfigurer() {
		return new WebMvcConfigurer() {
			public void addInterceptors(InterceptorRegistry registry) {
				// 注册拦截接口 拦截所有请求 基于方法 ,过滤器基于请求
				// 拦截器能否拦截静态资源？
				registry.addInterceptor(loginIntercept).addPathPatterns("/*");
				// 排除静态资源
			};
		};
	}

}
