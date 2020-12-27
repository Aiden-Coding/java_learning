package com.itmayiedu;

import org.apache.commons.lang3.StringUtils;

//// 汽车厂（4S店）
public class CarFactory {

	public static Car createCar(String name) {
		// 判斷name是否為空..
		if(StringUtils.isEmpty(name)){
			return null ;
		}
		if (name.equals("比亚迪")){
			return new BydCar();
		}
		if (name.equals("吉利")){
			return new JiLiCar();
		}
		// 改代码之后，可以不用重启服务器。 动态 使用缓存或者数据库+反射。 类型id、类型名称 类型class地址
		if (name.equals("奥迪")){
			return new JiLiCar();
		}
		if (name.equals("特斯拉")){
			return new JiLiCar();
		}
		//其他或者未找到。
		return null;
	}

}
