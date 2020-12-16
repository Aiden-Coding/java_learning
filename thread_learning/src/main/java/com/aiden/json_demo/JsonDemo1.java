package com.aiden.json_demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonDemo1 {
  public static void main(String[] args) {
    String jsonStr = "{\"sites\":[{\"name\":\"百度\",\"url\":\"www.baidu.com\"}]}";
    JSONObject jsonObject = new JSONObject();
    // 将json字符串转为jsonbject
    JSONObject jsonStrObject = jsonObject.parseObject(jsonStr);
    JSONArray jsonArray = jsonStrObject.getJSONArray("sites");
    for (Object object : jsonArray) {
      JSONObject stObject = (JSONObject) object;
      String name = stObject.getString("name");
      String url = stObject.getString("url");
      System.out.println(name + "---" + url);
    }
  }
}
