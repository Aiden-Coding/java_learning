package com.itmayiedu;

public class Test001 {

	public static void main(String[] args) {
		ExtHashMap extHashMap = new ExtHashMap<String, Object>();
		extHashMap.put("1号", "itmayiedu");
		extHashMap.put("2号", "itmayiedu");
		extHashMap.put("3号", "itmayiedu");
		extHashMap.put("4号", "itmayiedu");
		extHashMap.put("5号", "itmayiedu");
		extHashMap.put("6号", "itmayiedu");
		extHashMap.put("7号", "7itmayiedu");
		extHashMap.put("11号", "itmayiedu");
		extHashMap.put("22号", "22itmayiedu");
		extHashMap.put("23号", "22itmayiedu");
		extHashMap.put("24号", "22itmayiedu");
		extHashMap.put("28号", "22itmayiedu");
		extHashMap.put("30号", "22itmayiedu");
		extHashMap.print();
		extHashMap.put("31号", "22itmayiedu");

		System.out.println(extHashMap.get("22号") + "---" + extHashMap.get("7号"));
		extHashMap.print();
	}

}
