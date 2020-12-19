
package com.itmayiedu.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class PostProcessBean implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("beanName:" + beanName + "初始化开始");
		return bean;

	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanName:" + beanName + "初始化结束");
		return bean;

	}

}
