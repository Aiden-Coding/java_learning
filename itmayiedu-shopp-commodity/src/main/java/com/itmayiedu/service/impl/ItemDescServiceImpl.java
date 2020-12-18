package com.itmayiedu.service.impl;

import com.itmayiedu.api.service.ItemDescService;
import com.itmayiedu.common.api.BaseApiService;
import com.itmayiedu.dao.ItemDescDao;
import com.itmayiedu.entity.ItemDescEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ItemDescServiceImpl extends BaseApiService implements ItemDescService {
	@Autowired
	private ItemDescDao itemDescDao;

	@RequestMapping("/getItemDesc")
	public Map<String, Object> getItemDesc(@RequestParam("id") Long id) {
		ItemDescEntity itemDesc = itemDescDao.getItemDesc(id);
		return setResutSuccessData(itemDesc);
	}

}
