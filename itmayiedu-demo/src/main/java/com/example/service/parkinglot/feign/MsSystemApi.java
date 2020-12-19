package com.example.service.parkinglot.feign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="ms-system")
@Component
public interface MsSystemApi {

    @RequestMapping( value = "/getUserPermissionsInfo" , method = RequestMethod.POST)
    @LoadBalanced
    Map<String, Object> getUserPermissionsInfo(@RequestParam Map<String, Object> params);
}
