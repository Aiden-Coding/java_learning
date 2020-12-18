
package com.aiden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AppMemberServer {

	public static void main(String[] args) {
		SpringApplication.run(AppMemberServer.class, args);
	}

}
