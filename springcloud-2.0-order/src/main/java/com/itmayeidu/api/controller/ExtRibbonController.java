/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayeidu.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月6日 下午9:09:21<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
// 纯手写Ribbon本地负载均衡效果
@RestController
public class ExtRibbonController {
	// 可以获取注册中心上的服务列表
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private RestTemplate restTemplate;
	// 接口的请求总数
	private int reqCount = 1;

	@RequestMapping("/ribbonMember")
	public String ribbonMember() {
		// 1.获取对应服务器远程调用地址
		String instancesUrl = getInstances() + "/getMember";
		System.out.println("instancesUrl:" + instancesUrl);
		// 2.可以直接使用httpclient技术实现远程调用
		String result = restTemplate.getForObject(instancesUrl, String.class);
		return result;
	}
	// 使用原子计数器 因为线程安全 效率非常高 使用cas 无锁机制

	private String getInstances() {
		List<ServiceInstance> instances = discoveryClient.getInstances("app-itmayiedu-member");
		if (instances == null || instances.size() <= 0) {
			return null;
		}
		// 获取服务器集群个数
		int instanceSize = instances.size();
		int serviceIndex = reqCount % instanceSize;
		reqCount++;
		return instances.get(serviceIndex).getUri().toString();

	}

	@RequestMapping("/orderToUser")
	public ResponseEntity orderToUser(String name) {
		Map<String, String> map = new HashMap<>();
		map.put("name", name);
		String url = "http://app-itmayiedu-member/getUser?name={name}";
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class, map);
		return response;
	}

}
