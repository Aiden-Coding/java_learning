/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月4日 下午9:50:31<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
public class OrderController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private RestTemplate restTemplate;

	// 案例 使用 DiscoveryClient 使用服务别名获取会员服务实际调用的rpc远程地址
	@RequestMapping("/getMemberUrl")
	public String getMemberUrl(String serviceName) {
		String serviceUrl = "http://app-itmayiedu-member" + "/getMember";
		System.out.println("serviceUrl:" + serviceUrl);
		String result = restTemplate.getForObject(serviceUrl, String.class);
		return result;
	}

	// 使用服务别名向注册中心获取服务相关信息 ,可能获取的服务集群信息
	public String serviceUrl(String serviceName) {
		List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
		if (list != null && list.size() > 0) {
			return list.get(0).getUri().toString();
		}
		return null;
	}

}
