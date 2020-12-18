
package com.aiden.refactor;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.aiden.api.service.MbUserService;

@FeignClient(name = "member")
public interface RefMbUserService extends MbUserService {

}
