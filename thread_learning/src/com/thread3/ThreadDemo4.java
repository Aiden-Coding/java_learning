package com.thread3;

public class ThreadDemo4 {
  public static void main(String[] args) {
    PrioritytThread prioritytThread = new PrioritytThread();
    Thread t1 = new Thread(prioritytThread,"1");
    Thread t2 = new Thread(prioritytThread);
    t1.start();
    // 注意设置了优先级， 不代表每次都一定会被执行。 只是CPU调度会有限分配
    t1.setPriority(1);
    t2.start();
  }
}
