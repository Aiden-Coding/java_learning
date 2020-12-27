package com.syz.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
/**
 *编写消费者实例
 */

@RestController
public class rabbitTestCtl {

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String add() {
        return restTemplate.getForEntity("http://serveruser/hello", String.class).getBody();
    }
}
