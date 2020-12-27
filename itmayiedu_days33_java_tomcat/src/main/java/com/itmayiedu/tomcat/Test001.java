package com.itmayiedu.tomcat;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.startup.Tomcat.FixContextListener;

import com.itmayiedu.servlet.IndexServlet;

/**
 * ʹ��Java���Դ���Tomcat������<br>
 * Tomcat �ײ�ִ�г��� ����servlet<br>
 * SpringMVC�ײ�ʹ��servlet ��װ<br>
 * 
 * @����˵�� ÿ�ؽ���-��ʤ�� <br>
 * @��ϵ��ʽ qq644064779|www.itmayieducom-���Ͽ���<br>
 */
public class Test001 {
	private static int PORT = 8080;
	private static String CONTEX_PATH = "/itmayiedu";
	private static String SERVLET_NAME = "indexServlet";

	public static void main(String[] args) throws LifecycleException, InterruptedException {

		// ����tomcat������
		Tomcat tomcatServer = new Tomcat();
		// ָ���˿ں�
		tomcatServer.setPort(PORT);
		// �Ƿ������Զ�����
		tomcatServer.getHost().setAutoDeploy(false);
		// ����������
		StandardContext standardContex = new StandardContext();
		standardContex.setPath(CONTEX_PATH);
		// ����������
		standardContex.addLifecycleListener(new FixContextListener());
		// tomcat�������standardContex
		tomcatServer.getHost().addChild(standardContex);

		// ����Servlet
		tomcatServer.addServlet(CONTEX_PATH, SERVLET_NAME, new IndexServlet());
		// servleturlӳ��
		standardContex.addServletMappingDecoded("/index", SERVLET_NAME);
		tomcatServer.start();
		System.out.println("tomcat�����������ɹ�..");
		// �첽���н�������
		tomcatServer.getServer().await();

	}

}
