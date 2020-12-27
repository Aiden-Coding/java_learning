package com.itmayiedu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springmvc ������Ϣ
 * 
 * @EnableWebMvc ����springmvc ����<br>
 * @����˵�� ÿ�ؽ���-��ʤ�� <br>
 * @��ϵ��ʽ qq644064779|www.itmayieducom-���Ͽ���<br>
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.itmayiedu.controller" })
public class WebConfig extends WebMvcConfigurerAdapter {

	// springboot ����jsp �����war
	// ��Ҫ������ͼת����
	// ����SpringMVC��ͼ������
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		// ������JSPҳ����ͨ��${}����beans
		viewResolver.setExposeContextBeansAsAttributes(true);
		return viewResolver;
	}

}
