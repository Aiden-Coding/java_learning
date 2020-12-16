package com.thread3;

public class IntThrad1 extends Thread {
  private Res1 res;

  public IntThrad1(Res1 res) {
    this.res = res;
  }

  @Override
  public void run() {
    int count = 0;
    while (true) {
      synchronized (res) {
        if (res.flag) {
          try {
            // 当前线程变为等待，但是可以释放锁
            res.wait();
          } catch (Exception e) {

          }
        }
        if (count == 0) {
          res.userName = "";
          res.userSex = "男";
        } else {
          res.userName = "小紅";
          res.userSex = "女";
        }
        count = (count + 1) % 2;
        res.flag = true;
        // 唤醒当前线程
        res.notify();
      }
    }
  }
}
