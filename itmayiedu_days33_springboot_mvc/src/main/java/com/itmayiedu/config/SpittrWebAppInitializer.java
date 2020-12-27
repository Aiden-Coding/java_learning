package com.itmayiedu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * ����springmvc--dispatcherServlert<br>
 * 
 * @����˵�� ÿ�ؽ���-��ʤ�� <br>
 * @��ϵ��ʽ qq644064779|www.itmayieducom-���Ͽ���<br>
 */
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// ���ظ�������Ϣ spring����
	protected Class<?>[] getRootConfigClasses() {

		return new Class[] { RootConfig.class };
	}

	// springmvc ���� ������Ϣ
	protected Class<?>[] getServletConfigClasses() {

		return new Class[] { WebConfig.class };
	}

	// springmvc ����urlӳ�� ������������
	protected String[] getServletMappings() {

		return new String[] { "/" };

	}

}
