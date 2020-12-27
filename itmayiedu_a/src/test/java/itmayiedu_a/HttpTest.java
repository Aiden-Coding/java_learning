/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package itmayiedu_a;

import javax.crypto.Cipher;

import org.bouncycastle.jce.provider.JDKKeyFactory.RSA;

import com.alibaba.fastjson.JSONObject;
import com.itmayidu.des.DES;
import com.itmayiedu.rsa.RSAUtil;
import com.itmayiedu.utils.HttpClientUtils;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午3:13:25<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class HttpTest {
	// 公钥
	private static String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJLXS1FHjXJA8ZphwFhBulHdFY+al/eIyCOegungapQvks+43VKNTec5sfbGMF31lLbD6G02n8IvSpknrAhlTrkCAwEAAQ==";

	public static void main(String[] args) {

		// // 1. 先生成密钥对
		// RSAUtil.generateKey();
		// System.out.println("私钥：" + RSAUtil.privateKey);
		// System.out.println("公钥：" + RSAUtil.publicKey);
		String userName = "yushengjun";
		String encryptUserName = RSAUtil.encryptByPublicKey(userName, PUBLIC_KEY, Cipher.ENCRYPT_MODE);
		JSONObject reuslt = HttpClientUtils.httpGet("http://127.0.0.1:8080/getMember?userName=" + encryptUserName);
		System.out.println("reuslt:" + reuslt);
	}

}
