
package com.itmayiedu;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet("/ToFromServlet")
public class ToFromServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 创建默认连接
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建get请求
		HttpGet httpGet = new HttpGet("http://a.a.com/a/FromServlet?userName=" + req.getParameter("userName"));
		CloseableHttpResponse response = httpClient.execute(httpGet);
		int code = response.getStatusLine().getStatusCode();
		// 获取状态
		System.out.println("http请求结果:" + code);
		if (code == 200) {
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
			resp.getWriter().print(result);
			response.close();
			httpClient.close();
			// 将string转换html框架

		}

	}

}
