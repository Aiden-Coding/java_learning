
package com.itmayiedu.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //@ComponentScan("com.itmayiedu.app")
//@ComponentScan("com.itmayiedu.controller")
@MapperScan(basePackages={"com.itmayiedu.mapper"})
//@EnableAutoConfiguration
public class AppJsp {

	public static void main(String[] args) {
		SpringApplication.run(AppJsp.class, args);
	}

}
