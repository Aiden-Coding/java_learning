/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.itmayiedu.demo;

import org.I0Itec.zkclient.ZkClient;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月21日 下午5:36:10<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
public class Demo01 {

	public static void main(String[] args) {
		String connection = "192.168.212.161:2181,192.168.212.162:2181,192.168.212.163:2181";
		ZkClient zkClient = new ZkClient(connection);
		zkClient.createPersistent("/itmayiedu_yushengjun");
		zkClient.close();
	}
	// 如果在springboot项目中，需要引入的依赖如果需要加上版本号， 这是什么意思？ 因为SpringBoot 帮你集成
	// 1. 自己整合

}
