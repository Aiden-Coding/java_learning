package com.itmayiedu02;

//适配器
public class Adpater extends Adpatee   implements Target {

	@Override
	public void handleReq() {
		super.request();
	}

}
