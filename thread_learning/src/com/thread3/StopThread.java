package com.thread3;

public class StopThread implements Runnable {
  private boolean flag = true;

  @Override
  public synchronized void run() {
    while (flag) {
      try {
        wait();
      } catch (Exception e) {
        //e.printStackTrace();
        stopThread();
      }
      System.out.println("thread run..");
    }

  }

  public void stopThread() {
    flag = false;
  }
}
