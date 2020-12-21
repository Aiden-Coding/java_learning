package com.itmayiedu.fallback;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itmayiedu.service.MemberFeign;

@Component
public class MemberFallBack implements MemberFeign {

	public List<String> getToOrderMemberAll() {
	    //服务降级处理
		List<String> list = new ArrayList<String>();
		list.add("服务发生异常...");
		return list;
	}

}
