package com.syz.eureka.eurekacluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启服务注册中心的功能
@EnableEurekaServer
@SpringBootApplication
public class EurekaClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClusterApplication.class, args);
	}
}
