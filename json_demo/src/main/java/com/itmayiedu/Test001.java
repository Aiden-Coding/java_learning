
package com.itmayiedu;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.entity.User;

public class Test001 {
	static String json = "{\"id\":\"10\",\"name\":\"每特教育\",\"items\":[{\"itemId\":\"20\",\"itemName\":\"蚂蚁课堂\"},{\"itemId\":\"21\",\"itemName\":\"每特学院\"}]}";

	public static void main(String[] args) {
		// 1.解析json 定义json 转换成jsonObject
		JSONObject jsonObject = new JSONObject().parseObject(json);
		String id = jsonObject.getString("id");
		String name = jsonObject.getString("name");
		System.out.println(id + "-" + name);
		JSONArray jsonArray = jsonObject.getJSONArray("items");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = jsonArray.getJSONObject(i);
			String itemId = object.getString("itemId");
			String itemName = object.getString("itemName");
			System.out.println("itemId:" + itemId + "itemName:" + itemName);
		}
		// 2.使用java反射机制 对应生成对象
		User user = new JSONObject().parseObject(json, User.class);
		System.out.println("user:" + user.toString());
	}

}
