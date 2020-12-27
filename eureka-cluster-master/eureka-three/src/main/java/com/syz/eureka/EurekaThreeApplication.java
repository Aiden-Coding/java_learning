package com.syz.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启服务注册中心的功能
@EnableEurekaServer
@SpringBootApplication
public class EurekaThreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaThreeApplication.class, args);
	}
}
