
package com.aiden.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @classDesc: 功能描述:(Spring Cloud服务注册中心)



 * @createTime: 2017年10月23日 下午10:31:12
 * @version: v1.0

 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {
       SpringApplication.run(EurekaServer.class, args);
	}
}
