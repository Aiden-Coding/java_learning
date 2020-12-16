package com.aiden.annotation;

import java.lang.reflect.Field;

public class Main {
  public static void main(String[] args) throws ClassNotFoundException {
    // 1.反射class
    Class<?> classForName = Class.forName("com.entity.Sudent");
    // 2.获取表名称注解F
    SetTable setTable = classForName.getAnnotation(SetTable.class);
    // 3.获取所有的成员属性
    Field[] declaredFields = classForName.getDeclaredFields();
    StringBuffer sf = new StringBuffer();
    sf.append(" select ");
    String fromName = setTable.value();
    for (int i = 0; i < declaredFields.length; i++) {
      Field field = declaredFields[i];
      // 4.属性字段
      SetProperty sb = field.getAnnotation(SetProperty.class);
      sf.append(" " + sb.name() + " ");
      if (i == declaredFields.length - 1) {
        sf.append(" from ");
      } else {
        sf.append(" , ");
      }
    }
    sf.append(" " + fromName);
    System.out.println(sf.toString());
  }
}
