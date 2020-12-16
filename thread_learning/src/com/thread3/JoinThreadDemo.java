package com.thread3;

public class JoinThreadDemo {
  public static void main(String[] args) {
    JoinThread joinThread = new JoinThread();
    Thread t1 = new Thread(joinThread);
    Thread t2 = new Thread(joinThread);
    t1.start();
    t2.start();
    try {
      //其他线程变为等待状态，等t1线程执行完成之后才能执行主线程的方法。
      t1.join();
    } catch (Exception e) {

    }
    for (int i = 0; i < 100; i++) {
      System.out.println("main ---i:" + i);
    }
  }
}
