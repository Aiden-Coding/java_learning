
package com.itmayiedu.demo2;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ToFromServlet")
public class ToFromServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = UUID.randomUUID().toString();
		req.getSession().setAttribute("sessionToken", token);
		req.getRequestDispatcher("from.jsp").forward(req, resp);
	}

}
