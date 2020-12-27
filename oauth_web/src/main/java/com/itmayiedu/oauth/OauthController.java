/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.oauth;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.itmayiedu.utils.HttpClientUtils;
import com.itmayiedu.utils.WeiXinUtils;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月20日 下午9:52:11<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Controller
public class OauthController {

	@Autowired
	private WeiXinUtils weiXinUtils;

	// 1.生成授权链接 重定向跳转到微信开放平台
	@RequestMapping("/authorizedUrl")
	public String authorizedUrl() {
		String authorizedUrl = weiXinUtils.getAuthorizedUrl();
		System.out.println("authorizedUrl:" + authorizedUrl);
		// 直接重定向到微信开放平台
		return "redirect:" + authorizedUrl;
	}

	// 2.用户回调地址 ,返回Code
	@RequestMapping("/callback")
	@ResponseBody
	public String callback(String code) {
		// 1 第一步：用户同意授权，获取code
		System.out.println("code:" + code);
		if (StringUtils.isEmpty(code)) {
			return "errorPage";
		}
		// 2 第二步：通过code换取网页授权access_token(调用微信接口有权限)
		String accessTokenUrl = weiXinUtils.getAccessTokenUrl(code);
		// 使用HttpClient技术调用accessTokenUrl 接口地址
		JSONObject accessTokenResult = HttpClientUtils.httpGet(accessTokenUrl);
		// 3 第三步：刷新access_token（如果需要） 判断报文json中是否有errorCode
		boolean containsErrcodeKey = accessTokenResult.containsKey("errcode");
		if (containsErrcodeKey) {
			// 调用接口报错
			return "errorPage";
		}
		// 获取access_token
		String accessToken = accessTokenResult.getString("access_token");
		if (StringUtils.isEmpty(accessToken)) {
			// 调用接口报错
			return "errorPage";
		}
		// 获取openid
		String openId = accessTokenResult.getString("openid");
		if (StringUtils.isEmpty(openId)) {
			// 调用接口报错
			return "errorPage";
		}

		/// 4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
		String userInfo = weiXinUtils.getUserInfo(accessToken, openId);
		JSONObject userInfoResult = HttpClientUtils.httpGet(userInfo);
		System.out.println("userInfoResult:" + userInfoResult);

		// 5 附：检验授权凭证（access_token）是否有效
		return "success";
	}

}
