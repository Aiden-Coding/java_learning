
package com.itmayiedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImgFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("过滤器初始化...");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("doFilter...");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String referer = req.getHeader("referer");
		// 请求服务名称 http://a.a.com
		String serverName = req.getServerName();
		System.out.println("referer:" + referer + ",serverName:" + serverName);
		if (referer == null || !(referer.contains(serverName))) {
              //
			req.getRequestDispatcher("error.png").forward(req, res);
			return ;
		}
		
		//放行 
		chain.doFilter(req, res);
	}

	public void destroy() {

	}

}
