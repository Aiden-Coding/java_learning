package com.itmayiedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ��תҳ��<br>
 * 
 * @����˵�� ÿ�ؽ���-��ʤ�� <br>
 * @��ϵ��ʽ qq644064779|www.itmayieducom-���Ͽ���<br>
 */
@Controller
public class UserController {
  
	@RequestMapping("/pageIndex")
	public String pageIndex() {
		return "pageIndex";
	}

}
