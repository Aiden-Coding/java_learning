/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.demo;

import com.itmayidu.des.DES;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月21日 下午2:31:09<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class Demo01 {
	// 1.配置密钥
	private static String PASSWORD = "95880288";

	public static void main(String[] args) throws Exception {
		// 2.需要加密的内容
		String content = "yushengjun";
		// 3.使用DES 加密
		byte[] encryptContent = DES.encrypt(content.getBytes(), PASSWORD);
		System.out.println("加密后内容:" + new String(encryptContent));
		// 4.使用DES 解密
		byte[] decrypt = DES.decrypt(encryptContent, PASSWORD);
		System.out.println("解密后内容:" + new String(decrypt));
	}

}
