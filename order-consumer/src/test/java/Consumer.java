import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiden.service.UserService;



public class Consumer {

	 public static void main(String[] args) throws IOException {
		  ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("consumer.xml");
		  app.start();
		  UserService userService=(UserService) app.getBean("userService");
		  String name = userService.getUserId(2);
		  System.out.println("消费端从生产者获取到 name:"+name);
		  System.in.read();

	}

}
