package com.itmayiedu;

import javax.swing.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 演示springboot jvm参数调优<br>
 * 作者: 每特教育-余胜军<br>
 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
 */
@SpringBootApplication
public class SpringBootApp {

	/**
	 * 内部启动 <br>
	 * 
	 * -XX:+PrintGCDetails -Xmx4096M -Xms4096M <br>
	 * 打印详细GC日志 最大堆内存32m 初始堆内存为1m 预计会发生多少次回收？ 特别频繁 外部启动<br>
	 * 
	 * 作者: 每特教育-余胜军<br>
	 * 联系方式:QQ644064779|WWW.itmayiedu.com<br>
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootApp.class, args);
	}

}
