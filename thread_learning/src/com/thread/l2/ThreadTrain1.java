package com.thread.l2;

public class ThreadTrain1 implements Runnable {
  // 这是货票总票数,多个线程会同时共享资源
  private int trainCount = 100;

  @Override
  public void run() {
    while (trainCount > 0) {// 循环是指线程不停的去卖票
      try {
        // 等待100毫秒
        Thread.sleep(10);
      } catch (InterruptedException e) {

      }
      sale();
    }
  }

  public void sale() {
    if (trainCount > 0) {
      try {
        Thread.sleep(10);
      } catch (Exception e) {

      }
      System.out.println(Thread.currentThread().getName() + ",出售 第" + (100 - trainCount + 1) + "张票.");
      trainCount--;
    }
  }
}
