package com.syz.eureka.serveruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 *配置生产者
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServeruserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServeruserApplication.class, args);
	}
}
