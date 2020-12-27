/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package itmayiedu_b;

import java.net.URLDecoder;
import java.net.URLEncoder;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.utils.HttpClientUtils;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午9:19:21<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class Test001 {

	public static void main(String[] args) {
		// Java 提供Http协议 特殊字符转码类
		String encode = URLEncoder.encode("1+1");
		System.out.println("encode:" + encode);
		System.out.println("decode:" + URLDecoder.decode(encode));
		// String userNameUrl = "http://127.0.0.1:8080/indexPage%3FuserName=" +
		// URLEncoder.encode("1+1");
		// JSONObject result = HttpClientUtils.httpGet(userNameUrl);
		// System.out.println("result:" + result);
		// 注意事项 不要整个URL进行编码，只是针对于参数编码
	}

}
