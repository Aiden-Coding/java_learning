import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu02.service.UserService;

public class SpringTest003 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring3.xml");
		UserService userService = (UserService) app.getBean("userService");
		userService.add();
	}

}
