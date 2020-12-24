package com.itmayiedu.api.service.impl;

import org.springframework.web.bind.annotation.RestController;

import com.itmayiedu.api.service.WeiXinService;
import com.itmayiedu.base.BaseApiService;
import com.itmayiedu.base.ResponseBase;

@RestController
public class WeiXinServiceImpl extends BaseApiService implements WeiXinService {

	@Override
	public ResponseBase index() {

		return setResultSuccess();
	}

}
