
package com.itmayiedu.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class UserEntity implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean,
		DisposableBean, BeanPostProcessor {

	private String userName;
	private Integer age = null;

	public UserEntity() {
		System.out.println("1.无惨构造函数.....  对象初始化");
	}

	public UserEntity(String userName, Integer age) {
		System.out.println("我是有参构造函数 userName:" + userName + ",age:" + age);
		this.userName = userName;
		this.age = age;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {
		System.out.println("2.set userName  屬性注入");
		this.userName = userName;
	}

	public Integer getAge() {

		return age;
	}

	public void setAge(Integer age) {

		this.age = age;
	}

	@Override
	public String toString() {
		return "UserEntity [userName=" + userName + ", age=" + age + "]";
	}

	// 获取当前查找beanName
	public void setBeanName(String name) {
		System.out.println("3.setBeanName,name:" + name);
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

		System.out.println("4.set beanFactory ");

	}

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		System.out.println("beanName:" + beanName + "初始化开始");
		return bean;

	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanName:" + beanName + "初始化结束");
		return bean;

	}

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		System.out.println("5.setApplicationContext");

	}

	public void destroy() throws Exception {

		System.out.println("销毁bean");
	}

	public void afterPropertiesSet() throws Exception {

	}

}
