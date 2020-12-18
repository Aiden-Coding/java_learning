package com.itmayiedu.feign;

import com.itmayiedu.api.service.ItemDescService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("commodity")
public interface ItemDescFeign extends ItemDescService {

}
