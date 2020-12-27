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
 * 创建时间:2018年7月21日 下午4:50:09<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class Test003 {

	public static void main(String[] args) {

		// 1. 生成(公钥和私钥)密钥对
		RSAUtil.generateKey();
		System.out.println("公钥:" + RSAUtil.publicKey);
		System.out.println("私钥:" + RSAUtil.privateKey);
		System.out.println("----------私加密公钥解密-------------");
		// 使用 私加密,公钥解密
		String textsr = "yushengjun";
		String encryptByprivate = RSAUtil.encryptByprivateKey(textsr, RSAUtil.privateKey, Cipher.ENCRYPT_MODE);
		System.out.println("私钥加密后:" + encryptByprivate);
		String encryptByPublic = RSAUtil.encryptByPublicKey(encryptByprivate, RSAUtil.publicKey, Cipher.DECRYPT_MODE);
		System.out.println("公钥解密后:" + encryptByPublic);
	}

}
