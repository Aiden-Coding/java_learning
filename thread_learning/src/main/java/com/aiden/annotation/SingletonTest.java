package com.aiden.annotation;

public class SingletonTest {
  public static void main(String[] args) {
    Singleton sl1 = Singleton.getSingleton();
    Singleton sl2 = Singleton.getSingleton();
    System.out.println(sl1 == sl2);
  }
}

class Singleton {
  // 当需要的才会被实例化
  private static Singleton singleton;
  private Singleton() {
  }
  synchronized public static Singleton getSingleton() {
    if (singleton == null) {
      singleton = new Singleton();
    }
    return singleton;
  }
}
