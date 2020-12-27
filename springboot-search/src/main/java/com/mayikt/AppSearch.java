package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = { "com.mayikt.repository" })
@SpringBootApplication
public class AppSearch {

	public static void main(String[] args) {
		SpringApplication.run(AppSearch.class, args);
	}

}
