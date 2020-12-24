package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092100564758";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCliRZUblIkCFcKpu0/FbvEIEzyTsK73uj8l3MFfygrfSmX3rG3IE5T4+6c+IIVQlqhzJNTUqFCr1IoV7wR51H9om89LxVygbwYyYyZiTw+8zbRTkp4u1rDoxpKAiQVAQHzwfV273x97Jyen9IXfR9Z10bP8z+o49GM3Tr+bPzQ9qvLCE1QBeutzBuIRrjboB+eaffX1vl1T1CE/1yOheBu8jNIUjbjrG8FlHOGSSWvtWdd/JDLv+yp1RcdTtAsivPdbWEiwppEggjm5ybpBuevTmmUEyr+tmTJQg22z0S5PsDOX+H5CPARlkHIgiBFXfzv5dd0VQtPzZ5wV4L88P+/AgMBAAECggEAJULWY+rxUmsOLLemb6zUtzEOIPzZmLB0gWcV3QbiY+eolSbrme0LtJZ5/5twrJhjDkieYQMYiYotVCdrjhwjuJP3ti4DBeNuE6ZKn8TkascUb8jPup1xsNfl8wLIXHH9noDqX/tIao/4MTyIbgEcLXzn06dNjRa69JpXzS17Ow3usGDH5sjq3jPioCKxPqY4I/EqOtwW7V6EfcWXYoMueF6Z1qGGVRPegQbfeCbt24fTRAbzCrla1ibXxNFfAvKg/BTTW6CVWmYTyLyjXqZYhB7Alm8gxzQsAcDtzR34sDTOzXcrfjWmjuPgKn23MeQ3JDyOBF80KoF3D04RpPkjAQKBgQDOzvcJGxEXDpgmZeUArhKkK1XDSjqRvcU698xi5LQwqlMwDpyzxrBZOhebTcDLaj65PLmWDsAhlOUU0yNA+Lqeef+0Q8tl3YCrmryZzA6dINphrWzqe5ufOKHmyzO6yX/xSeD7NV0UIG9alN592eSVSOwgg/qBRNFQAf0xq4VDrQKBgQDM6OsVbdylS+1rEaFHbQGz6k9gxQhh/+nX+d2lHWs2yWRje1ENjLQcGJVFFFN+Uvhd9X3/be3tPgBtQ1kHWmEz0HJnufgFSacoSRrK1biVV6Dt/SUEJo8N7j/QDCq1j3EVcp87W0CZx+CsFsyyzvSHKJPGeLtLH7ZPQCw1xvXemwKBgAHqWtFjxcm/4drhMLjL4/JPUHEJm/dDmbMNHoB/+8dDnJeiWwMzQOjip9tRYgrARipzDIj+Q/tLpL+HoRj19MyI9rxVsGmWHp34RezqvevopP0onQyi7nMQFDfOBAGj4iJ6+7F38g/AqSden5zsyrgzP7ShZE2UNJgYHJ88JnTVAoGAPVz7GX2+99lXDhtHewZP3jsq7kXTDtX+h2kSUSIQO2XwJEKHsVETlt5dxUQjBM+ABVNBGj8nY0aQBiiG/sa4GTYxLJLqjVrTLiNGXLvH/SEy1lJaxXVunbNddMZP+/Mq4EtlF/dpzyOmSqLBRkLnw5oJNwRswkCK7gy/sJMWikUCgYEApKAlyCsujMLoMr8cqrBG3cdxq7z+MdqU6a5VrxrK4YnOef0BQmU4caq2ym2wX/1FvHs1k3/adymdoH7ntdK9YQWz7MS83AR0vdzxeVwdqsRQYIdi5N7YbYKnjL/3LgaqymYyPsgmRXrfg2tjVUrRHV7VUz/gLCLPJDyYzan93ck=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm
	// 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1U7j3Bl5QtyF6ZGouhkUFivy4lA10SQlZFP4CBY9uigwMDljVnhhQ/8yfRCHokJ6ZYaHfRivNWxm0zjtmKCNILN8HNnPtEx7lSChPSLkbwAvCtwhgPgtQFXYDlRvRACDfLw9uetUZXP7z0sd1RH8X7LVJ0d/+KN3ODlPLCcjCWXR08uHW7ys4Yrtv2AePnntvSFNNUtGOLG3mbv+sWW14I/++10KwejhnkBaMiBkx8FLcOWvT4bjArLH8TynT4Gd66GJjbCXY1jHELvKuLguBRWL6Ya22iN1udq99GQY6l8Z5trtQhOoCCj0MiAp3V8DRlwVTvg9Hn2raTENpCiJNQIDAQAB";
	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://itmayiedu.tunnel.qydev.com/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://itmayiedu.tunnel.qydev.com/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * 
	 * @param sWord
	 *            要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
