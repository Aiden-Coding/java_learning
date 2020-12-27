/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.zuulfilter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年9月11日 下午5:23:29<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Component
public class TokenFilter extends ZuulFilter {

	public Object run() throws ZuulException {
		// 获取上下文
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		String userToken = request.getParameter("userToken");
		if (StringUtils.isEmpty(userToken)) {
			currentContext.setSendZuulResponse(false);
			currentContext.setResponseStatusCode(401);
			currentContext.setResponseBody("userToken is null");
			return null;
		}
		// 否则正常执行业务逻辑.....
		return null;
	}

	// 判断过滤器是否生效
	public boolean shouldFilter() {

		return true;
	}

	// 过滤器的执行顺序。当请求在一个阶段的时候存在多个多个过滤器时，需要根据该方法的返回值依次执行
	public int filterOrder() {

		return 0;
	}

	// 过滤器类型 pre 表示在 请求之前进行拦截
	public String filterType() {

		return "pre";
	}

}
