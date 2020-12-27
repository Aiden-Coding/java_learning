/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.itmayiedu.filter.TokenFilter;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月23日 下午10:10:39<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class AppZuul {

	// @EnableZuulProxy 开启Zuul网关代理
	// @EnableEurekaClient 注册到EurekaC

	public static void main(String[] args) {
		SpringApplication.run(AppZuul.class, args);
	}

	// 注册到SpringBoot 容器
	@Bean
	public TokenFilter accessFilter() {
		return new TokenFilter();
	}

}
