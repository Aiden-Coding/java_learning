/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayeidu.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月12日 下午8:56:54<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ZkOrderController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;

	// springcloud 中使用那些技术实现调用服务接口 feign 或者rest
	@RequestMapping("/orderToMember")
	public String orderToMember() {
		String memberUrl = "http://zk-member/getMember";
		return restTemplate.getForObject(memberUrl, String.class);
	}

	@RequestMapping("/discoveryClientMember")
	public List<ServiceInstance> discoveryClientMember() {
		List<ServiceInstance> instances = discoveryClient.getInstances("zk-member");
		for (ServiceInstance serviceInstance : instances) {
			System.out.println("url:" + serviceInstance.getUri());
		}
		return instances;

	}

	public static void main(String[] args) {
		SpringApplication.run(ZkOrderController.class, args);
	}

	// 默认rest方式开启 负载均衡功能 如果以app-itmayiedu-member名称进行调用服务接口的时候 必须
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	// 如何获取到注册中心上服务列表信息
}
