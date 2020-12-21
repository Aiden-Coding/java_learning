package com.example.service.parkinglot.feign;

import com.example.service.parkinglot.service.Fallback;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "ms-finance", fallback = Fallback.class)
public interface MsFinanceApi {
	@RequestMapping(value = "/saveTemporaryParkingDetail", method = RequestMethod.POST)
	@LoadBalanced
	Map<String, Object> saveTemporaryParkingDetail(@RequestParam Map<String, Object> params);
}
