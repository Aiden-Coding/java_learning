package com.mayikt.member.feign;

import org.springframework.cloud.openfeign.FeignClient;

import com.mayikt.weixin.service.VerificaCodeService;

@FeignClient("app-mayikt-weixin")
public interface VerificaCodeServiceFeign extends VerificaCodeService {

}
