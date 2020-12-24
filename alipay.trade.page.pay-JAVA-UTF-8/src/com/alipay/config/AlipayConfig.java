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
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092100564758";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCuPU9pSi7L+TChirG/2OtotzWKOiso/2lzRZxZkX2MWAvUr7+LZHY0Xns37tvxBzqh3gapkSpJWh/M8eeGfe43fKckTRgC5iRTwfVn9MaLE5Xj6cxT4GrW3c+jsxO79VY7MD8i/YSxU8sccu/IUF8WOVFVx83n42M6oO2xKwyeHZAYgXwVM7qn8Ts6VSpIt/FUyjrd5S6+6YB/4knLJEGqNV7p0kVjLuujeVdZYtGeJQE/QZVoU1cFv6xSLKvgqv2YjLvHPo86ioNk7nB2vWZo6u2Go06AOgvg9KSyJUTNzQCMCSqCTyj5BOcLnXs+P+AQuUww6y5wQ83u42Gc+5yDAgMBAAECggEARBk2RkJ2E8Zq2VgprH+NUjn4tPOTFszsW98jAma4mPd4eEHQLRywjjs3EV9LmuJmagkqtW9FBbIxp5oMESZ6yjjv4mI3NjrYi9vMnDqwtn6LIK6p60ZUu3Gpy6Mb3WQ/tZZftr8680t6oL3TNYkkAL2aKlqTAUR/7dQj0hCvN8RT8TQ3W6pZ9aq6DPT9aDMzK6Dw0WvGyPxSN+3r4ja0TWJWU/gIy+h1iIKC12VQCBHW4i+sH8PCydX7OztMm/jMSqQ/mB/vx38noEHkMcAmYe+RCqB/dqnpfxhBWFUjUpr+ttHfr9RwDQWcp2bHgvA5cwKjoDqMgG5BbL34IEqqIQKBgQDtFxl3uv1RfaKpJwaH1GwWnmajVGaKDgwq9knpwVVajeZn/YbuCGe/1TNLdgBkYO7M4vr5IR+Q7Z+4r1GVT0eVTryN+Vw81ZgwY6sOgg8oeNdq8ZJQNj1WGNNGlb/2Y7iWhLerubHE0cf0v229sgQBsuZfxJ7Err9Ho7AnDBpuewKBgQC8Iu0omABskC8wyrJL48TvNoSRxtCyRpyT10V2XWBrBH69xW6TBcWeF/PwFZhxYg2JiJskISTqmSqim7wBugMT0fQOk9xJPrfYUWJcdl3Dvmdk+f7sG3qUY9PitmgbtGVbUYgpCJQeE7ZNqFJfUnfU+rJ+scI9ykkJ3y9sE4UvmQKBgQC58LHSit38uTGGw8qFYH1cx+HrGlhI0XeNgha5sxyDpmIbEGRv7juf4U8XUXZBQxxFp4X5OJcPDK2FJEoxdTnIyTVrizNhqopu+NvAxxOoRA1+G/MJ4V/9U/7ujqc8Tz0HHxMFJN9cYsIVGsSwTxqBUpMGbKevzPTK9H9EQGPCXQKBgQCpdbA2q0tefjTzdvBIaR3e9jVM9U7P9chdZj78EbmiS4c5uhcSONW6xAqGi30nS9Gzuyc60NvmsBX2J96dk4XoGWoCo259Tw/budqmPDvS77NEAeZ7l3i/JngZMoHtynytV3AUvdRkjw7bPi0MDUVAwpt6gPTcIVPOn4igiaiiqQKBgDydvo659oOQ/KdC678LyokDSHCAGYeZ2iXbNGPFnFOGkGW0XsuCkHKpI5zmXiIkC4y0V7tzvOvcy4LrLks+wOn6Vwyd12QqJYXHvTQPrU0vEWjgQdJnkftwRS7oJVm7G7qXVXsiP4eyR9A+YoXPQivaPG4khCxNppBNyU9Oqwl1";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIgHnOn7LLILlKETd6BFRJ0GqgS2Y3mn1wMQmyh9zEyWlz5p1zrahRahbXAfCfSqshSNfqOmAQzSHRVjCqjsAw1jyqrXaPdKBmr90DIpIxmIyKXv4GGAkPyJ/6FTFY99uhpiq0qadD/uSzQsefWo0aTvP/65zi3eof7TcZ32oWpwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
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

