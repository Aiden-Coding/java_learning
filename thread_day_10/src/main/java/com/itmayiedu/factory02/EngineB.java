package com.itmayiedu.factory02;

public class EngineB  implements Engine {

	public void run() {
		System.out.println("转的慢..");
		
	}

	public void start() {
		System.out.println("启动慢，手动档");
		
	}

}
