package com.itmayiedu.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.fallback.MemberFallBack;


@FeignClient(value = "service-member",fallback=MemberFallBack.class)
public interface MemberFeign {

	@RequestMapping("/getMemberAll")
	public List<String> getToOrderMemberAll();

}
