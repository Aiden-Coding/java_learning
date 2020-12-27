package com.syz.eureka.eurekaone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启服务注册中心的功能
@EnableEurekaServer
@SpringBootApplication
public class EurekaOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaOneApplication.class, args);
	}
}
