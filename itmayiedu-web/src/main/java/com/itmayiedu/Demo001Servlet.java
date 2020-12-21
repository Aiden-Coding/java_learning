
package com.itmayiedu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Demo001Servlet")
public class Demo001Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Thread.sleep(3000);
			resp.getWriter().println("this is  demo001Servlet....");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
