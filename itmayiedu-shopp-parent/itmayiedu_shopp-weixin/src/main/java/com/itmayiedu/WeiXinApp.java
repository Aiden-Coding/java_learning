package com.itmayiedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

@Component(value = "me.chanjar.weixin.mp.api")
@EnableEurekaClient
@SpringBootApplication
public class WeiXinApp {

	public static void main(String[] args) {
		SpringApplication.run(WeiXinApp.class, args);
	}

}
