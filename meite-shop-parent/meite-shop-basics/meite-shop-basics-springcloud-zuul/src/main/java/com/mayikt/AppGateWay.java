package com.mayikt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.spring4all.swagger.EnableSwagger2Doc;

import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 
 * 
 * @description: 微服务网关入口
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableSwagger2Doc
@EnableApolloConfig
public class AppGateWay {

	// 获取config
	@ApolloConfig
	private Config config;

	@Value("${mayikt.zuul.swagger.document}")
	private String swaggerDocument;

	public static void main(String[] args) {
		SpringApplication.run(AppGateWay.class, args);
	}

	// 添加文档来源
	@Component
	@Primary
	class DocumentationConfig implements SwaggerResourcesProvider {

		// 访问swagger-ui页面的每次都会范围访问一下get方法
		// 定义value 注解获取和手动获取区别是什么？
		@SuppressWarnings("rawtypes")
		@Override
		public List<SwaggerResource> get() {
			// app-itmayiedu-order
			// 网关使用服务别名获取远程服务的SwaggerApi
			return resources();
		}

		/**
		 * 从阿波罗服务器中获取resources
		 * 
		 * @return
		 */
		private List<SwaggerResource> resources() {

			List resources = new ArrayList<>();
			// app-itmayiedu-order
			// 网关使用服务别名获取远程服务的SwaggerApi
			// String swaggerDocJson = swaggerDocument();
			JSONArray jsonArray = JSONArray.parseArray(swaggerDocument);
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				String name = jsonObject.getString("name");
				String location = jsonObject.getString("location");
				String version = jsonObject.getString("version");
				resources.add(swaggerResource(name, location, version));
			}
			return resources;
		}

		// /**
		// * 获取swaggerDocument配置
		// *
		// * @return
		// */
		// private String swaggerDocument() {
		// String property = config.getProperty("mayikt.zuul.swagger.document",
		// "");
		// return property;
		// }

		private SwaggerResource swaggerResource(String name, String location, String version) {
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(name);
			swaggerResource.setLocation(location);
			swaggerResource.setSwaggerVersion(version);
			return swaggerResource;
		}

	}

}
