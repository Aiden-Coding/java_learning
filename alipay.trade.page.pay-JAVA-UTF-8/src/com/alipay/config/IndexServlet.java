package com.alipay.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

@WebServlet("/alipay")
public class IndexServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		// resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 付款金额，必填
		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
		// 订单名称，必填
		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
		// 商品描述，可空
		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no
		// +"\","
		// + "\"total_amount\":\""+ total_amount +"\","
		// + "\"subject\":\""+ subject +"\","
		// + "\"body\":\""+ body +"\","
		// + "\"timeout_express\":\"10m\","
		// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		// 请求
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// String rs = "<form name='punchout_form' method='post'
		// action='https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=oC5cgjWEkXmotSWXYpPbK1R%2FeS%2BZRQYV2acqsHzrAP4smTE01mrkvb1%2BIZ6pFqdjuv44Vwi0fC%2BCXLkDwr07bh9c%2F5DZBAQoIbRF37SsWFQfZHA5knSLYbNSTFmXAhABxQheZ1jT35Sd0zmzuB3blVtRhgLI9aXEeldFm%2Fb9AHBf%2FcUlGKbKwxFZh4QP4%2B8ZcqOKFwFcrMR7rgJlUbb0Qr6wRN18FZx9FQUVjMAqL0K0Ntyx84PAHfIvU0gxtzrnAurTGgbUnYzG6nWzXtxao25YNH%2Fq3n5NlejAkBiB0Zw%2Fhsdq7yhodDgzObReUfxiVjatuh%2FtUghP4lvV9ZPsFQ%3D%3D&return_url=http%3A%2F%2Fitmayiedu.tunnel.qydev.com%2Freturn_url.jsp&notify_url=http%3A%2F%2Fitmayiedu.tunnel.qydev.com%2Fnotify_url.jsp&version=1.0&app_id=2016092100564758&sign_type=RSA2&timestamp=2018-03-23+10%3A10%3A49&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json'><input
		// type='hidden' name='biz_content'
		// value='{&quot;out_trade_no&quot;:&quot;201832310714261&quot;,&quot;total_amount&quot;:&quot;0.01&quot;,&quot;subject&quot;:&quot;测试&quot;,&quot;body&quot;:&quot;&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}'><input
		// type='submit' value='立即支付' style='display:none'
		// ></form><script>document.forms[0].submit();</script>";
		// String rs1="<form name='punchout_form' method='post'
		// action='https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=oC5cgjWEkXmotSWXYpPbK1R%2FeS%2BZRQYV2acqsHzrAP4smTE01mrkvb1%2BIZ6pFqdjuv44Vwi0fC%2BCXLkDwr07bh9c%2F5DZBAQoIbRF37SsWFQfZHA5knSLYbNSTFmXAhABxQheZ1jT35Sd0zmzuB3blVtRhgLI9aXEeldFm%2Fb9AHBf%2FcUlGKbKwxFZh4QP4%2B8ZcqOKFwFcrMR7rgJlUbb0Qr6wRN18FZx9FQUVjMAqL0K0Ntyx84PAHfIvU0gxtzrnAurTGgbUnYzG6nWzXtxao25YNH%2Fq3n5NlejAkBiB0Zw%2Fhsdq7yhodDgzObReUfxiVjatuh%2FtUghP4lvV9ZPsFQ%3D%3D&return_url=http%3A%2F%2Fitmayiedu.tunnel.qydev.com%2Freturn_url.jsp&notify_url=http%3A%2F%2Fitmayiedu.tunnel.qydev.com%2Fnotify_url.jsp&version=1.0&app_id=2016092100564758&sign_type=RSA2&timestamp=2018-03-23+10%3A10%3A49&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json'><input
		// type='hidden' name='biz_content'
		// value='{"out_trade_no":"201832310714261","total_amount":"0.01","subject":"测试","body":"","product_code":"FAST_INSTANT_TRADE_PAY"}'><input
		// type='submit' value='立即支付' style='display:none'
		// ></form><script>document.forms[0].submit();</script>";

		// 输出
		System.out.println("result:" + result);
		out.println(result);
		// out.println("<form>");
		// out.println("</form>");

	}
}
