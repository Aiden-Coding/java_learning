package com.thread.l2;

public class ThreadTrain2 implements Runnable {
  // 这是货票总票数,多个线程会同时共享资源
  private int trainCount = 100;
  public boolean flag = true;

  @Override
  public void run() {
    if (flag) {
      while (true) {
        synchronized (this) {
          if (trainCount > 0) {
            try {
              Thread.sleep(40);
            } catch (Exception e) {

            }
            System.out.println(Thread.currentThread().getName() + ",出售 第" + (100 - trainCount + 1) + "张票.");
            trainCount--;
          }
        }
      }
    } else {
      while (true) {
        sale();
      }
    }
  }

  public synchronized void sale() {
    if (trainCount > 0) {
      try {
        Thread.sleep(40);
      } catch (Exception e) {

      }
      System.out.println(Thread.currentThread().getName() + ",出售 第" + (100 - trainCount + 1) + "张票.");
      trainCount--;
    }
  }
}
