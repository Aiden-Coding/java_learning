import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws IOException {
		// 加载配置文件
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("provider.xml");
		applicationContext.start();
		System.out.println("会员服务启动成功...");
		System.in.read();/// 一直能够保证服务启动..
	}

}
