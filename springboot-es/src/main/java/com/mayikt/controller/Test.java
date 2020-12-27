package com.mayikt.controller;

public class Test {
	public static void main(String[] args) {
		String value = "蚂蚁课堂手写Dubbo框架";
		String keyWord = "蚂蚁课堂";
		value.replace(keyWord, "<a  style=‘color : red'>" + keyWord + "</a>");

	}
}
