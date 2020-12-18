package com.itmayiedu.feign;

import com.itmayiedu.api.service.ItemService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient("commodity")
public interface ItemFeign extends ItemService {

}
