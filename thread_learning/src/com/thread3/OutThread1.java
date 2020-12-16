package com.thread3;

public class OutThread1 extends Thread {
  private Res1 res;

  public OutThread1(Res1 res) {
    this.res = res;
  }

  @Override
  public void run() {
    while (true) {
      synchronized (res) {
        if (!res.flag) {
          try {
            res.wait();
          } catch (Exception e) {
            // TODO: handle exception
          }
        }
        System.out.println(res.userName + "--" + res.userSex);
        res.flag = false;
        res.notify();
      }
    }
  }
}
