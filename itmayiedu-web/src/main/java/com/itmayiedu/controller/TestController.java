
package com.itmayiedu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itmayiedu.ItmayieduService;

@WebServlet("/index")
public class TestController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		ItmayieduService itmayieduService = new ItmayieduService();
		String result = itmayieduService.getUser(id);
		req.setAttribute("user", result);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
