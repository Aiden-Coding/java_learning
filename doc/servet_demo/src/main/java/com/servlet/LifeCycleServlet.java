
package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @classDesc: 功能描述:(servlet生命周期)
 * @author: 余胜军
 * @createTime: 2017年8月31日 下午11:23:41
 * @version: v1.0
 * @copyright:上海每特教育科技有限公司
 */
public class LifeCycleServlet  extends HttpServlet{
 
	public LifeCycleServlet(){
		System.out.println("LifeCycleServlet 执行构造函数..");
	}
	 @Override
	public void init() throws ServletException {
		 System.out.println("LifeCycleServlet初始化... init()");
		    
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 System.out.println("LifeCycleServlet初始化... doGet()");  
	}
	@Override
	public void destroy() {
		 System.out.println("LifeCycleServlet初始化... destroy()");  
	}
	
}
