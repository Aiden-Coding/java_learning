package com.itmayiedu.demo2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

@WebServlet("/FromServlet")
public class FromServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我在执行get请求");
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我在执行post请求");
		String userName = req.getParameter("userName");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userName", userName);
//		String jsonpCallback = req.getParameter("jsonpCallback");
//		resp.getWriter().println(jsonpCallback + "(" + jsonObject.toJSONString() + ")");
		resp.getWriter().println(jsonObject.toJSONString() );
		// //允许浏览器跨域问题 允许所有的
		// resp.setHeader("Access-Control-Allow-Origin", "*");

	}
}
