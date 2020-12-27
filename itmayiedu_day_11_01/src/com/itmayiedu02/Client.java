package com.itmayiedu02;

/**
 * 客户端类
 * 
 * @author Administrator
 *
 */
public class Client {
	public void test1(Target t) {
		t.handleReq();
	}

	public static void main(String[] args) {
		Client c = new Client();
		Adpatee a = new Adpatee();
		Adpater t=new Adpater();
		c.test1(t);
	}
}
