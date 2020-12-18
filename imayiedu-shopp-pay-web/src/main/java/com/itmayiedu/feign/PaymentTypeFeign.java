package com.itmayiedu.feign;

import com.itmayiedu.service.PaymentTypeService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("pay-service")
public interface PaymentTypeFeign extends PaymentTypeService {

}
