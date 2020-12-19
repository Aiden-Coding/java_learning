//
//package com.itmayiedu;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.itmayiedu.entity.Item;
//import com.itmayiedu.entity.User;
//
//public class Test002 {
//
//	public static void main(String[] args) {
//		// 1.json api 封装json
//		JSONObject root = new JSONObject();
//		root.put("id", "01");
//		root.put("name", "张三");
//		JSONArray arrayList = new JSONArray();
//		JSONObject object1 = new JSONObject();
//		object1.put("itemId", "20");
//		object1.put("itemName", "蚂蚁课堂");
//		JSONObject object2 = new JSONObject();
//		object2.put("itemId", "30");
//		object2.put("itemName", "每特学院");
//		arrayList.add(object1); 
//		arrayList.add(object2);
//		root.put("items", arrayList);
//		System.out.println(root.toJSONString());
//		//2.将对象转换成json字符串
//		User user = new User();
//		user.setId("01");
//		user.setName("张三");
//		List<Item> items =new ArrayList<Item>();
//		Item item1 = new Item();
//		item1.setItemId("20");
//		item1.setItemName("蚂蚁课堂");
//		Item item2 = new Item();
//		item2.setItemId("30");
//		item2.setItemName("每特学院");
//		items.add(item1);
//		items.add(item2);
//		user.setItems(items);
//		System.out.println(new JSONObject().toJSONString(user));
//	}
//
//}
