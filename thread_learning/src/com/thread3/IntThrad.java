package com.thread3;

public class IntThrad extends Thread {
  private Res res;

  public IntThrad(Res res) {
    this.res = res;
  }

  @Override
  public void run() {
    int count = 0;
    while (true) {
      synchronized (res) {
        if (count == 0) {
          res.userName = "";
          res.userSex = "男";
        } else {
          res.userName = "小紅";
          res.userSex = "女";
        }
      }
      count = (count + 1) % 2;
    }
  }
}
