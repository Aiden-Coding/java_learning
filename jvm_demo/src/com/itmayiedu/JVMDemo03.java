package com.itmayiedu;

/**
 * 设置jvm老年代比例
 * @author Administrator
 *
 */
public class JVMDemo03 {

	 public static void main(String[] args) {
		//-Xms20m -Xmx20m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
		// -XX:NewRatio=2

		 byte [] b = null;
		 for (int i = 0; i < 10; i++) {
			b =new byte[1*1024*1024];
		}

	}
	
}
