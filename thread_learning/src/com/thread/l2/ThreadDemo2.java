package com.thread.l2;

public class ThreadDemo2 {
  public static void main(String[] args) {
    ThreadTrain1 threadTrain = new ThreadTrain1(); // 定义 一个实例
    Thread thread1 = new Thread(threadTrain, "一号窗口");
    Thread thread2 = new Thread(threadTrain, "二号窗口");
    thread1.start();
    thread2.start();
  }
}
