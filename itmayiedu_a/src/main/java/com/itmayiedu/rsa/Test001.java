/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.rsa;

import javax.crypto.Cipher;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午10:30:59<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class Test001 {

	public static void main(String[] args) {

		// 实现步骤：
		// 1.生成公钥和私钥密钥对
		RSAUtil.generateKey();
		System.out.println("私钥:" + RSAUtil.privateKey);
		System.out.println("公钥:" + RSAUtil.publicKey);
		String content = "yushengjun644";
		// 2.使用公钥进行加密
		String encryptByPublicKey = RSAUtil.encryptByPublicKey(content, RSAUtil.publicKey, Cipher.ENCRYPT_MODE);
		System.out.println("加密后:" + encryptByPublicKey);
		String encryptByprivateKey = RSAUtil.encryptByprivateKey(encryptByPublicKey, RSAUtil.privateKey,
				Cipher.DECRYPT_MODE);
		// 3.使用私钥进行解密
		System.out.println("解密后:" + encryptByprivateKey);
		// 正常在开发中的时候,后端开发人员生成好密钥对，服务器端保存私钥 客户端保存公钥

	}

}
