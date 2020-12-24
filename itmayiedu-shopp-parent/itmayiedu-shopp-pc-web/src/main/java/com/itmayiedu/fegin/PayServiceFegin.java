package com.itmayiedu.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.itmayiedu.api.service.PayService;

@FeignClient("pay")
public interface PayServiceFegin extends PayService {

}
