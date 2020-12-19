
package com.itmayiedu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServet01 extends HttpServlet {
	private volatile int i = 1;

	/**
	 * 无惨构造函数 只初始化一次说明DemoServet01是一个单例。
	 */
	public DemoServet01() {
		System.out.println("DemoServet01");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("初始化");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("处理请求");
			resp.setCharacterEncoding("utf-8");// 内容编码，防止出现中文乱码
			resp.setContentType("text/html;charset=utf-8");
			synchronized (DemoServet01.class) {
				resp.getWriter().write("这是第" + i + "次访问...");
				Thread.sleep(5000);
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public void destroy() {
		System.out.println("销毁...");

	}

}
