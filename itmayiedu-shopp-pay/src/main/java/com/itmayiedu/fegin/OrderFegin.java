package com.itmayiedu.fegin;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import com.itmayiedu.order.api.service.OrderService;

@FeignClient("order")
@Component
public interface OrderFegin  extends OrderService {

}
