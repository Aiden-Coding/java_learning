package com.itmayiedu.servie;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.error.MemberFeignService;

//fallback = MemberFeignService.class
@FeignClient(value = "service-member")
public interface MemberFeign {
	@RequestMapping("/getMemberAll")
	public List<String> getOrderByUserList();
}
