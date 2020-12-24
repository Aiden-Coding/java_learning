package com.itmayiedu.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class TestUrl {

	public static void main(String[] args) {
		String userNameEncode = URLEncoder.encode("yusheng+jun");
		System.out.println("userNameEncode:" + userNameEncode);
		String userNameDecode = URLDecoder.decode(userNameEncode);
		System.out.println("userNameDecode:" + userNameDecode);
	}

}
