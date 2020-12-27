package com.itmayiedu.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

import com.itmayiedu.servlet.IndexServlet;

/**
 * 使用Java语言创建Tomcat服务器<br>
 * Tomcat 底层执行程序 最终servlet<br>
 * SpringMVC底层使用servlet 包装<br>
 * 
 * @作者说明 每特教育-余胜军 <br>
 * @联系方式 qq644064779|www.itmayieducom-蚂蚁课堂<br>
 */
public class Test001 {
	private static int PORT = 8080;
	private static String CONTEX_PATH = "/itmayiedu";
	private static String SERVLET_NAME = "indexServlet";

	public static void main(String[] args) throws LifecycleException, InterruptedException {

		// 创建tomcat服务器
		Tomcat tomcatServer = new Tomcat();
		// 指定端口号
		tomcatServer.setPort(PORT);
		// 是否设置自动部署
		tomcatServer.getHost().setAutoDeploy(false);
		// 创建上下文
		StandardContext standardContex = new StandardContext();
		standardContex.setPath(CONTEX_PATH);
		// 监听上下文
		standardContex.addLifecycleListener(new FixContextListener());
		// tomcat容器添加standardContex
		tomcatServer.getHost().addChild(standardContex);

		// 创建Servlet
		tomcatServer.addServlet(CONTEX_PATH, SERVLET_NAME, new IndexServlet());
		// servleturl映射
		standardContex.addServletMappingDecoded("/index", SERVLET_NAME);
		tomcatServer.start();
		System.out.println("tomcat服务器启动成功..");
		// 异步进行接收请求
		tomcatServer.getServer().await();

	}

}
