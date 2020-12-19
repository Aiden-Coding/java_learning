
package com.itmayiedu;

import java.beans.Encoder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

interface Hose {
	void maifang();
}

class XiaoMing implements Hose {

	@Override
	public void maifang() {
		System.out.println("我是小明,我要去买房啦!");
	}

}

// 代理类
class Proxy implements Hose {
	private XiaoMing xiaoMing;

	public Proxy(XiaoMing xiaoMing) {
		this.xiaoMing = xiaoMing;
	}

	@Override
	public void maifang() {
		System.out.println("我是中介，小明买房开始.....");
		xiaoMing.maifang();
		System.out.println("我是中介，小明买房结束.....");
	}

}

// jdk 动态代理
class JdkProxy implements InvocationHandler {
	private Object tarjet;

	public JdkProxy(Object tarjet) {
		this.tarjet = tarjet;
	}

	@Override
	public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) throws Throwable {
		System.out.println("我是中介，小明买房开始.....");
		Object oj = paramMethod.invoke(tarjet, paramArrayOfObject);
		System.out.println("我是中介，小明买房结束.....");
		return oj;

	}

}

// cglib动态代理
class Cglib implements MethodInterceptor {

	@Override
	public Object intercept(Object paramObject, Method method, Object[] paramArrayOfObject, MethodProxy methodProxy)
			throws Throwable {
		System.out.println("我是中介，小明买房开始.....");
		Object o = methodProxy.invokeSuper(paramObject, paramArrayOfObject);
		System.out.println("我是中介，小明买房结束.....");
		return o;

	}

}

public class Test003 {

	public static void main(String[] args) {
		// Hose hose = new Proxy(new XiaoMing());
		// hose.maifang();
		XiaoMing xiaoMing = new XiaoMing();
		Cglib cglib = new Cglib();
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(XiaoMing.class);
		enhancer.setCallback(cglib);
		Hose hose = (Hose) enhancer.create();
		hose.maifang();
		// JdkProxy jdkProxy = new JdkProxy(xiaoMing);
		// Hose hose = (com.itmayiedu.Hose)
		// java.lang.reflect.Proxy.newProxyInstance(xiaoMing.getClass().getClassLoader(),
		// xiaoMing.getClass().getInterfaces(), jdkProxy);
		// hose.maifang();
	}

}
