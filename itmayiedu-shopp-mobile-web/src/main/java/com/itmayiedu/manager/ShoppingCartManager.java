package com.itmayiedu.manager;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ShoppingCartManager {
	public Map<String, Object> addShopping(String userId, String itemId);

	public Map<String, Object> remoShopping(String userId, String itemId);

}
