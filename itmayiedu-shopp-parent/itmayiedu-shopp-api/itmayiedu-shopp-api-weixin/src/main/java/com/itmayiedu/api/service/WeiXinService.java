package com.itmayiedu.api.service;

import org.springframework.web.bind.annotation.RequestMapping;

import com.itmayiedu.base.ResponseBase;

@RequestMapping("/weixin")
public interface WeiXinService {

	@RequestMapping("/index")
	public ResponseBase index();

}
