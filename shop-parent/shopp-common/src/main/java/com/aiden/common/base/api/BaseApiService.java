
package com.aiden.common.base.api;

import java.util.HashMap;
import java.util.Map;

import com.aiden.constants.Constants;

public class BaseApiService {

	// 封装成功
	public Map<String, Object> setResultSuccess() {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, null);
	}

	public Map<String, Object> setResultData(Object data) {
		return setResult(Constants.HTTP_RES_CODE_200, Constants.HTTP_RES_CODE_200_VALUE, data);
	}

	// 失败
	public Map<String, Object> setResultError(String msg) {
		return setResult(Constants.HTTP_RES_CODE_500, msg, null);
	}

	// 自定义
	public Map<String, Object> setResult(Integer code, String msg, Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(Constants.HTTP_RES_CODE_NAME, code);
		resultMap.put(Constants.HTTP_RES_CODE_MSG, msg);
		if (data != null)
			resultMap.put(Constants.HTTP_RES_CODE_DATA, data);
		return resultMap;
	}

}
