
package com.itmayiedu.lock;

public class OrderService implements Runnable {
  private OrderNumGenerator orderNumGenerator = new OrderNumGenerator();
  private Lock lock = new ZookeeperDistrbuteLock();


  public void run() {
    getOrderNumber();
  }

  // 获取订单编号
  public void getOrderNumber() {
    lock.getLock();

    // synchronized (this) {

    try {
      Thread.sleep(50);
      String orderNum = orderNumGenerator.orderNum();
      System.out.println("线程名称" + Thread.currentThread().getName() + "获取生成的订单号为:" + orderNum);
    } catch (Exception e) {
      // TODO: handle exception
    }
    // }
    lock.unLock();
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(new OrderService()).start();
    }
  }

}
