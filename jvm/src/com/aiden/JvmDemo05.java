package com.aiden;

import java.util.Vector;

public class JvmDemo05 {

	public static void main(String[] args) {
		Vector v = new Vector(10000000);
		for (int i = 1; i < 100000000; i++) {
			Object o = new Object();
			v.add(o);
			o = null;
		}
	}

}
