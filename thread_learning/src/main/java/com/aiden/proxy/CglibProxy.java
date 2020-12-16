package com.aiden.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

  @Override
  public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
    System.out.println("我是买房中介 ， 开始监听你买房了....");
    Object invokeSuper = methodProxy.invokeSuper(o, args);
    System.out.println("我是买房中介 ， 开结束你买房了....");
    return invokeSuper;
  }
  public static void main(String[] args) {
    CglibProxy cglib = new CglibProxy();
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(XiaoMing.class);
    enhancer.setCallback(cglib);
    Hose hose = (Hose) enhancer.create();
    hose.mai();
  }
}
