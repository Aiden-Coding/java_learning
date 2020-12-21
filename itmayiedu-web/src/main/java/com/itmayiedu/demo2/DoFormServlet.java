package com.itmayiedu.demo2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/DoFormServlet")
public class DoFormServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		if(!isFlag(req)){
			resp.getWriter().write("fail");
			System.out.println("已经重复提交....");
			return;
		}
		String userName = req.getParameter("userName");
		try {
			Thread.sleep(300);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("往数据库插入数据...." + userName);
		resp.getWriter().write("success");
	}

	public boolean isFlag(HttpServletRequest request) {
		String token = request.getParameter("sessionToken");
		if (StringUtils.isEmpty(token)) {
			System.out.println("token为null");
			return false;
		}
		String sessionToken = (String) request.getSession().getAttribute("sessionToken");
		if (StringUtils.isEmpty(sessionToken)) {
			System.out.println("sessionToken为null，请不要重复提交..");
			return false;
		}
		if (!sessionToken.equals(token)) {
			System.out.println("请不要伪造token...sessionToken:" + sessionToken + ",token:" + token);
			return false;
		}
		//删除token
		 request.getSession().removeAttribute("sessionToken");
		return true;

	}

}
