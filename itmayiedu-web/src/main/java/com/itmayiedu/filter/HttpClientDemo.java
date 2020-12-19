
package com.itmayiedu.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo {
	public static void main(String[] args) {
		//get();
		post();
	}

	static public void get() {
		try {
			// 创建默认连接
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 创建get请求
			HttpGet httpGet = new HttpGet("https://kyfw.12306.cn/otn/leftTicket/queryO?leftTicketDTO.train_date=2018-01-01&leftTicketDTO.from_station=SHH&leftTicketDTO.to_station=WHN&purpose_codes=ADULT");
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			// 获取状态
			System.out.println("http请求结果:" + code);
			if (code == 200) {
				System.out.println(EntityUtils.toString(response.getEntity()));
				//将string转换html框架
			}
			response.close();
			httpClient.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	static public void post() {
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost("http://127.0.0.1/a/FromServlet");
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("userName", "yushengjun"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
