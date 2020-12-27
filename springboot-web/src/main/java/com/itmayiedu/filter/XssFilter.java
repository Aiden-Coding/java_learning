
package com.itmayiedu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.itmayiedu.httprequest.XssHttpServletRequestWrapper;

//// 过滤器 拦截所有请求 XSS 攻击
// @WebFilter(filterName = "xssFilter", urlPatterns = "/*")
// public class XssFilter implements Filter {
//
// public void init(FilterConfig filterConfig) throws ServletException {
//
// }
//
// public void doFilter(ServletRequest request, ServletResponse response,
//// FilterChain chain)
// throws IOException, ServletException {
// System.out.println("64404");
// HttpServletRequest req = (HttpServletRequest) request;
// XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new
//// XssHttpServletRequestWrapper(req);
// chain.doFilter(xssHttpServletRequestWrapper, response);
// }
//
// public void destroy() {
//
// }
// }
