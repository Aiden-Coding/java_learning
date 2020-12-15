package com.thread.l2;

public class ThreadDemo21 {
  public static void main(String[] args) throws InterruptedException {
    ThreadTrain2 threadTrain = new ThreadTrain2(); // 定义 一个实例
    Thread thread1 = new Thread(threadTrain, "一号窗口");
    Thread thread2 = new Thread(threadTrain, "二号窗口");
    thread1.start();
    Thread.sleep(40);
    threadTrain.flag = false;
    thread2.start();
  }
}
