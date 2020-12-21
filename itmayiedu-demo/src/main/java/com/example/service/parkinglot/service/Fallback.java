package com.example.service.parkinglot.service;

import com.example.service.parkinglot.feign.MsFinanceApi;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Fallback implements MsFinanceApi {
	//
	// public Map<String, Object> saveTemporaryParkingDetail(Map<String, Object>
	// params) {
	//
	// //TODO
	// Auto-generated method stub
	// return null;
	//
	// }
	@Override
	public Map<String, Object> saveTemporaryParkingDetail(Map<String, Object> params) {
		Map map = new HashMap();
		map.put("status", 500);
		map.put("name", "saveTemporaryParkingDetail");
		return map;
	};

}
