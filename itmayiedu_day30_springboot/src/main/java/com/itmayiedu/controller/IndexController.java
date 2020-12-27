package com.itmayiedu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class IndexController {
	@Value("${itmayiedu}")
	private String itmayieduName;

	@RequestMapping("/index")
	public String index() {
		String result = "springboot2.0 V1.0";
		log.info("result:{}", result);
		return result + itmayieduName;
	}

}
