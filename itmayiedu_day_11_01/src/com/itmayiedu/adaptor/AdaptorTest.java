package com.itmayiedu.adaptor;

import java.io.OutputStreamWriter;

public class AdaptorTest {

	public static void main(String[] args) {
		// 220V电源接口
		CN220VInterface cN220VInterface = new CN220VInterfaceImpl();
		// 适配器接口
		PowerAdaptor powerAdaptor = new PowerAdaptor(cN220VInterface);
		// 电饭煲
		ElectricCooker electricCooker = new ElectricCooker(powerAdaptor);
		electricCooker.cook();

	}

}
