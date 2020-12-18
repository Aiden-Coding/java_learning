
package com.aiden.api.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;

public interface TestService {
	@RequestMapping("/test")
	public Map<String, Object> test();
}
