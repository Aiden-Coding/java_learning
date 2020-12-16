package com.thread3;

public class ThreaCommun {
  public static void main(String[] args) {
    Res1 res = new Res1();
    IntThrad1 intThrad = new IntThrad1(res);
    OutThread1 outThread = new OutThread1(res);
    intThrad.start();
    outThread.start();
  }
}
