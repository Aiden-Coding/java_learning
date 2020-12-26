package com.itmayiedu;

import java.util.ArrayList;
import java.util.List;

public class ProductListGenerator {
	/**
	 * 接收一个表示列表大小的int参数，并返回一个生成产品的List列表
	 * 
	 * @param size
	 * @return
	 */
	public List<Product> generate(int size) {
		List<Product> ret = new ArrayList<Product>();
		for (int i = 0; i < size; i++) {

			Product product = new Product();

			product.setName("Product " + i);

			product.setPrice(10);

			ret.add(product);

		}

		return ret;

	}
}