package com.itmayiedu.adaptor;

//电源适配器
public class PowerAdaptor implements JP110VInterface {
	// 中国220V接口
	private CN220VInterface cN220VInterface;

	public PowerAdaptor(CN220VInterface cN220VInterface) {
		this.cN220VInterface = cN220VInterface;
	}

	@Override
	public void connect() {
		cN220VInterface.connect();
	}

}
