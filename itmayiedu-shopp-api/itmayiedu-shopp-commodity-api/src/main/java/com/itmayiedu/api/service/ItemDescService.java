package com.itmayiedu.api.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 商品详情
 *
 * @author Administrator
 *
 */
@RequestMapping("/item")
public interface ItemDescService {

	/**
	 * 查询商品详情
	 *
	 * @return
	 */
	@RequestMapping("/getItemDesc")
	public Map<String, Object> getItemDesc(@RequestParam("id") Long id);

}
