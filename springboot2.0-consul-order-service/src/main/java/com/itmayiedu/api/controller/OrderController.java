/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月1日 下午6:43:44<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class OrderController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private LoadBalancerClient loadBalancer;

	public URI serviceUrl(String key) {
		List<ServiceInstance> list = discoveryClient.getInstances(key);
		if (list != null && list.size() > 0) {
			return list.get(0).getUri();
		}
		return null;
	}

	@RequestMapping("/getorder")
	public String getOrder() {
		// order 使用rpc 远程调用技术 调用 会员服务
		// String serviceUrl = serviceUrl("consul-member").toString();
		// System.out.println("serviceUrl:" + serviceUrl);
		// String memberUrl = "http://consul-member/getMember";
		// ServiceInstance serviceInstance =
		// loadBalancer.choose("consul-member");
		// System.out.println("服务地址：" + serviceInstance.getUri());
		// System.out.println("服务名称：" + serviceInstance.getServiceId());
		String result = restTemplate.getForObject("http://192.168.18.220:8501/getMember", String.class);
		System.out.println("会员服务调用订单服务,result:" + result);
		return result;
	}

}
