package com.aiden.annotation;

public class SingletonTest1 {
  public static void main(String[] args) {
    Singleton1 sl1 = Singleton1.getSingleton();
    Singleton1 sl2 = Singleton1.getSingleton();
    System.out.println((sl1 == sl2) + "-");
  }
}

class Singleton1 {
  //当class 文件被加载初始化
  private static Singleton1 singleton = new Singleton1();

  private Singleton1() {
  }

  public static Singleton1 getSingleton() {
    return singleton;
  }
}
