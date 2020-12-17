package com.aiden;

public class JvmDemo02 {

	 public static void main(String[] args) {
		//-Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
		//-XX:NewRatio=老年代/新生代
		 byte [] b = null;
		 for (int i = 0; i < 10; i++) {
			b =new byte[1*1024*1024];
		}

	}

}
