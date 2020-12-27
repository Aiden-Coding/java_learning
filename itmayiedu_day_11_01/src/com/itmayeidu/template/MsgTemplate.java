package com.itmayeidu.template;

//短信模板
public abstract class MsgTemplate {

	public void sendMsg() {

		// 1.开始日志报文
		addHeadLog();
		// 2.调用具体不同运用商发送
		httpRqeust();
		// 3.结束日志报文
		addFootLog();

	}

	public abstract void httpRqeust();

	private void addFootLog() {
		System.out.println("调用运营商结束记录日志...");

	}

	private void addHeadLog() {
		System.out.println("调用运营商开始记录日志... ");
	}

}
