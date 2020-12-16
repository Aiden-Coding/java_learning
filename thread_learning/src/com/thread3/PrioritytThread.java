package com.thread3;

public class PrioritytThread implements Runnable {

  public void run() {
    for (int i = 0; i < 100; i++) {
      System.out.println(Thread.currentThread().toString() + "---i:" + i);
    }
  }
}
