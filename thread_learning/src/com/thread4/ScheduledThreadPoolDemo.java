package com.thread4;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {
  public static void main(String[] args) {
    ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(5);
    newScheduledThreadPool.schedule(new Runnable() {
      public void run() {
        System.out.println("delay 3 seconds");
      }
    }, 3, TimeUnit.SECONDS);
  }
}
