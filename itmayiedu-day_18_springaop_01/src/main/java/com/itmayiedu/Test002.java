package com.itmayiedu;

import java.util.ArrayList;
import java.util.Date;

public class Test002 {

	// @Override 表示为重写
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Deprecated
	public static void add() {
		// // api 过时
		// new Date().parse("");
	}

	public static void main(String[] args) {
		new ArrayList<>();
		add();
	}

}
