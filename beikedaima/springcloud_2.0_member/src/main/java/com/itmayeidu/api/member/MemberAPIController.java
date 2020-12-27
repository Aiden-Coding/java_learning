/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayeidu.api.member;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring4all.swagger.EnableSwagger2Doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月11日 下午2:30:40<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@RestController
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2Doc
@Api("会员服务接口")
public class MemberAPIController {
	@Value("${server.port}")
	private String serverPort;

	@ApiOperation("会员服务首页接口")
	// @RequestMapping("/")
	@GetMapping("/")
	public String index(HttpServletRequest req) {
		System.out.println("我是首页.....");
		return "我是member会员服务" + serverPort;
	}

	public static void main(String[] args) {
		SpringApplication.run(MemberAPIController.class, args);
	}

}
