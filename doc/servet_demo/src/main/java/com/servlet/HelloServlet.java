
package com.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置编码格式
		// resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");// 内容编码，防止出现中文乱码
		resp.setContentType("text/html;charset=utf-8");
		// 向浏览器输出内容
		resp.getWriter().write("这是第一个动态网站的动态资源：" + new Date());

	}

}
