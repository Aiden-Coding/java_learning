
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itmayiedu02.service.UserService;

public class SpringTest4 {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("spring4.xml");
		UserService userService = (UserService) app.getBean("");
		userService.add03();

		// AbstractRefreshableApplicationContext
		// BeanDefinition

	}

}
