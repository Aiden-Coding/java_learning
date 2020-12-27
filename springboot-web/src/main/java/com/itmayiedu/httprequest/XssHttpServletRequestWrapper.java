/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.httprequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

// 重写HttpServletRequestWrapper 防止XSS攻击
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private HttpServletRequest request;

	/**
	 * @param request
	 */
	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		// 过滤getParameter参数 检查是否有特殊字符
		String value = super.getParameter(name);
		System.out.println("value:" + value);
		if (!StringUtils.isEmpty(value)) {
			// 将中文转换为字符编码格式，将特殊字符变为html源代码保存
			value = StringEscapeUtils.escapeHtml(value);
			System.out.println("newValue:" + value);
		}
		return value;
	}

}
