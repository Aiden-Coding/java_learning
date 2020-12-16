package com.thread3;

public class OutThread extends Thread {
  private Res res;

  public OutThread(Res res) {
    this.res = res;
  }

  @Override
  public void run() {
    while (true) {
      synchronized (res){
        System.out.println(res.userName + "--" + res.userSex);
      }
    }
  }
}
