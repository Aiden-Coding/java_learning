/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

// 验证Tomcat 参数
public class TokenFilter extends ZuulFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#run()
	 */
	public Object run() throws ZuulException {

		// 拦截参数执行业务逻辑
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(token)) {
			// 直接不能够继续执行下面业务逻辑
			ctx.setSendZuulResponse(false); // 不继续执行下面业务逻辑
			ctx.setResponseStatusCode(500);// 不继续执行下面业务逻辑
			ctx.setResponseBody("token is null");
			return null;
		}
		// 继续正常执行业务逻辑
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.IZuulFilter#shouldFilter()
	 */
	public boolean shouldFilter() {

		return true; // 是否开启当前ilter(
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netflix.zuul.ZuulFilter#filterOrder()
	 */
	@Override
	public int filterOrder() {

		return 0; // 过滤器优先级 数字越大 越优先执行大
	}

	@Override
	public String filterType() {

		return "pre";// 前置执行
	}

}
