package com.aiden.proxy;

public class CustomProxy implements Hose {
  private XiaoMing xiaoMing;
  public CustomProxy(XiaoMing xiaoMing) {
    this.xiaoMing = xiaoMing;
  }
  public void mai() {
    System.out.println("我是中介 看你买房开始啦!");
    xiaoMing.mai();
    System.out.println("我是中介 看你买房结束啦!");
  }
  public static void main(String[] args) {
    Hose proxy = new CustomProxy(new XiaoMing());
    proxy.mai();
  }
}
