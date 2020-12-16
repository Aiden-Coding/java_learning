package com.thread4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
  public static void main(String[] args) {
    final ExecutorService newCachedThreadPool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 10; i++) {
      final int index = i;
      newCachedThreadPool.execute(new Runnable() {
        public void run() {
          try {
            Thread.sleep(1000);
          } catch (Exception e) {
            // TODO: handle exception
          }
          System.out.println("i:" + index);
        }
      });
    }
  }
}
