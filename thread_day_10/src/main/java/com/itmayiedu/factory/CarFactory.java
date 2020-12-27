package com.itmayiedu.factory;

import org.apache.commons.lang3.StringUtils;

public class CarFactory {

	public static Car createCar(String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}
		if(name.equals("奥迪")){
			return new AoDi();
		}
		if(name.equals("吉利")){
			return new JiLi();
		}
		return null;

	}

}
