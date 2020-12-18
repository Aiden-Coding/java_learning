
package com.aiden.api.service.impl;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aiden.api.service.TestService;
import com.aiden.common.base.api.BaseApiService;

@RestController
public class TestServiceImpl extends BaseApiService implements TestService {

	@RequestMapping("/test")
	public Map<String, Object> test() {
		return setResultSuccess();

	}

}
