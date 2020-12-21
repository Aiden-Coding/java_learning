package com.itmayiedu.servie;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-member")
public interface MemberFeign {
	@RequestMapping("/getUserList")
	public List<String> getOrderByUserList();
}
