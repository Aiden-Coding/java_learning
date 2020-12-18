package com.itmayiedu.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CallbackService {
	/**
	 * 同步回调
	 *
	 * @return
	 */
	public Map<String, String> syn(HttpServletRequest request);

	/**
	 * 异步回调
	 *
	 * @return
	 */
	public String asyn(HttpServletRequest request);

}
