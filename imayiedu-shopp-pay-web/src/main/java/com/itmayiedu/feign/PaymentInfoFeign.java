package com.itmayiedu.feign;

import com.itmayiedu.service.PaymentInfoService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("pay-service")
public interface PaymentInfoFeign extends PaymentInfoService {

}
