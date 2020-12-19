
package com.itmayiedu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RsaServet")
public class RsaServet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// resp.sendRedirect("/a/LocaServet");
		resp.setStatus(302);
		resp.setHeader("Location", "/a/LocaServet");
	}

}
