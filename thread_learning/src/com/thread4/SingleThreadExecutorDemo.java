package com.thread4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
  public static void main(String[] args) {
    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 10; i++) {
      final int index = i;
      newSingleThreadExecutor.execute(new Runnable() {
        @Override
        public void run() {
          System.out.println("index:" + index);
          try {
            Thread.sleep(200);
          } catch (Exception e) {
            // TODO: handle exception
          }
        }
      });
    }
  }
}
